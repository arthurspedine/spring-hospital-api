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
public class ValidateUniqueUserLogin implements MedicValidationHandler, ReceptionistValidationHandler {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final String exceptionMessage = "There is already a medic with the same Login provided.";

    @Override
    public void validate(MedicRequestDTO data) {
        if (employeeRepository.existsUserByLogin(data.login()).isPresent())
            throw new ValidationException(exceptionMessage);
    }

    @Override
    public void validate(ReceptionistRequestDTO data) {
        if (employeeRepository.existsUserByLogin(data.login()).isPresent())
            throw new ValidationException(exceptionMessage);
    }
}
