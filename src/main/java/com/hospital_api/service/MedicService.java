package com.hospital_api.service;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.domain.employee.EmployeeType;
import com.hospital_api.domain.employee.MedicRequestDTO;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.repository.MedicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MedicRepository repository;


    public Medic saveMedic(MedicRequestDTO data) {
        if (repository.findByCrm(data.crm()).isPresent())
            throw new ValidationException("There is already a medic with the same CRM provided.");
        if (repository.existsUserByLogin(data.login()).isPresent())
            throw new ValidationException("There is already a medic with the same Login provided.");

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
}
