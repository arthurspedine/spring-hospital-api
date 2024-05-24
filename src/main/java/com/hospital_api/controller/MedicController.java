package com.hospital_api.controller;

import com.hospital_api.domain.employee.EmployeeType;
import com.hospital_api.domain.employee.MedicRequestDTO;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.dto.employee.medic.MedicDetailDTO;
import com.hospital_api.repository.MedicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee/medic")
public class MedicController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createMedic(@RequestBody @Valid MedicRequestDTO data, UriComponentsBuilder uriBuilder) {
        if (repository.findByCrm(data.crm()).isPresent() || repository.existsUserByLogin(data.login()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
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

        URI uri = uriBuilder.path("/employee/medic/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicDetailDTO(medic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDetailDTO> medicDetail(@PathVariable Long id) {
        Medic medic = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medic not found!"));
        MedicDetailDTO response = new MedicDetailDTO(medic);
        return ResponseEntity.ok(response);
    }
}
