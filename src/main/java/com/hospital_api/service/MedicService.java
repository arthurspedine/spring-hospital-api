package com.hospital_api.service;

import com.hospital_api.domain.employee.EmployeeType;
import com.hospital_api.domain.employee.medic.MedicRequestDTO;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.employee.validations.medic.MedicValidationHandler;
import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.dto.employee.medic.MedicDetailDTO;
import com.hospital_api.repository.MedicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MedicRepository repository;

    @Autowired
    private List<MedicValidationHandler> validations;


    public Medic saveMedic(MedicRequestDTO data) {
        validations.forEach(v -> v.validate(data));

        Medic medic = new Medic();
        medic.setName(data.name());
        medic.setCpf(data.cpf());
        medic.setCrm(data.crm());
        medic.setSpecialty(data.specialty());
        medic.setType(EmployeeType.MEDIC);

        String encryptedPassword = encoder.encode(data.password());
        User user = new User(null, data.login(), encryptedPassword, UserRole.ADMIN, medic);

        medic.setUser(user);
        repository.save(medic);

        return medic;
    }

    public Medic getMedicById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medic not found!"));
    }

    public Page<MedicDetailDTO> getAllMedics(Pageable page) {
        return repository.findAll(page).map(MedicDetailDTO::new);
    }
}
