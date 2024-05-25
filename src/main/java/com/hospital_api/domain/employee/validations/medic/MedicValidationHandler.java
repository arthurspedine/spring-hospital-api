package com.hospital_api.domain.employee.validations.medic;

import com.hospital_api.domain.employee.medic.MedicRequestDTO;

public interface MedicValidationHandler {
    void validate(MedicRequestDTO data);
}
