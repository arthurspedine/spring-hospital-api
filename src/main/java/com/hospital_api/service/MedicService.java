package com.hospital_api.service;

import com.hospital_api.domain.employee.EmployeeType;
import com.hospital_api.dto.employee.medic.MedicEditDTO;
import com.hospital_api.dto.employee.medic.MedicRequestDTO;
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

    private final String notFound = "Medic not found!";


    public Medic saveMedic(MedicRequestDTO data) {
        validations.forEach(v -> v.validate(data));

        Medic medic = new Medic();
        medic.setName(data.name());
        medic.setCpf(data.cpf());
        medic.setCrm(data.crm());
        medic.setSpecialty(data.specialty());
        medic.setType(EmployeeType.MEDIC);

        String encryptedPassword = encoder.encode(data.password());
        User user = new User(data.login(), encryptedPassword, UserRole.ADMIN, medic);

        medic.setUser(user);
        repository.save(medic);

        return medic;
    }

    public Medic getMedicById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(notFound));
    }

    public Page<MedicDetailDTO> getAllMedics(Pageable page) {
        return repository.findAll(page).map(MedicDetailDTO::new);
    }

    public Medic editMedic(MedicEditDTO data) {
        Medic medic = repository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException(notFound));
        medic.setName(data.name());
        return medic;
    }
}
