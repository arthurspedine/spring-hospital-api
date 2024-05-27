package com.hospital_api.domain.employee.validations;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.dto.employee.medic.MedicRequestDTO;
import com.hospital_api.dto.employee.receptionist.ReceptionistRequestDTO;
import com.hospital_api.domain.employee.validations.medic.MedicValidationHandler;
import com.hospital_api.domain.employee.validations.receptionist.ReceptionistValidationHandler;
import com.hospital_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUniqueCPF implements MedicValidationHandler, ReceptionistValidationHandler {

    @Autowired
    private EmployeeRepository repository;

    private final String exceptionMessage = "There is already an employee with the same CPF provided.";

    @Override
    public void validate(MedicRequestDTO data) {
        if (repository.findByCpf(data.cpf()).isPresent()) {
            throw new ValidationException(exceptionMessage);
        }
    }

    @Override
    public void validate(ReceptionistRequestDTO data) {
        if (repository.findByCpf(data.cpf()).isPresent()) {
            throw new ValidationException(exceptionMessage);
        }
    }
}
