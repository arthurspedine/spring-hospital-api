package com.hospital_api.service;

import com.hospital_api.domain.employee.EmployeeType;
import com.hospital_api.domain.employee.receptionist.Receptionist;
import com.hospital_api.dto.employee.receptionist.ReceptionistEditDTO;
import com.hospital_api.dto.employee.receptionist.ReceptionistRequestDTO;
import com.hospital_api.domain.employee.validations.receptionist.ReceptionistValidationHandler;
import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.dto.employee.receptionist.ReceptionistDetailDTO;
import com.hospital_api.repository.ReceptionistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionistService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ReceptionistRepository repository;

    @Autowired
    private List<ReceptionistValidationHandler> validations;

    private final String notFound = "Receptionist not found!";

    public Receptionist saveReceptionist(ReceptionistRequestDTO data) {
        validations.forEach(v -> v.validate(data));

        Receptionist receptionist = new Receptionist();
        receptionist.setName(data.name());
        receptionist.setCpf(data.cpf());
        receptionist.setShift(data.shift());
        receptionist.setType(EmployeeType.RECEPTIONIST);

        String encryptedPassword = encoder.encode(data.password());
        User user = new User(data.login(), encryptedPassword, UserRole.ADMIN, receptionist);

        receptionist.setUser(user);
        repository.save(receptionist);

        return receptionist;
    }

    public Receptionist getReceptionistById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(notFound));
    }

    public Page<ReceptionistDetailDTO> getAllReceptionists(Pageable page) {
        return repository.findAll(page).map(ReceptionistDetailDTO::new);
    }

    public Receptionist editReceptionist(ReceptionistEditDTO data) {
        Receptionist receptionist = repository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException(notFound));
        if (data.name() != null) {
            receptionist.setName(data.name());
        }
        if (data.shiftType() != null) {
            receptionist.setShift(data.shiftType());
        }
        return receptionist;
    }
}
