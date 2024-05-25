package com.hospital_api.domain.employee.validations.medic;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.domain.employee.medic.MedicRequestDTO;
import com.hospital_api.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUniqueCRM implements MedicValidationHandler{

    @Autowired
    private MedicRepository repository;

    @Override
    public void validate(MedicRequestDTO data) {
        if (repository.findByCrm(data.crm()).isPresent())
            throw new ValidationException("There is already a medic with the same CRM provided.");
    }
}
