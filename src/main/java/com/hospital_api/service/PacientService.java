package com.hospital_api.service;

import com.hospital_api.domain.pacient.Pacient;
import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.dto.pacient.PacientEditDTO;
import com.hospital_api.dto.pacient.PacientRequestDTO;
import com.hospital_api.repository.PacientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacientService {

    @Autowired
    private PacientRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Pacient savePacient(PacientRequestDTO data) {
        Pacient pacient = new Pacient();
        pacient.setName(data.name());
        pacient.setCpf(data.cpf());
        pacient.setBirthDate(data.date());
        pacient.setBloodType(data.bloodType());

        String encryptedPassword = encoder.encode(data.password());
        User user = new User(data.login(), encryptedPassword, UserRole.USER, pacient);

        pacient.setUser(user);
        repository.save(pacient);

        return pacient;
    }

    public Pacient getPacientById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pacient not found!"));
    }

    public Pacient editPacient(PacientEditDTO data) {
        Pacient pacient = repository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException("Pacient not found!"));
        pacient.setName(data.name());
        return pacient;
    }
}
