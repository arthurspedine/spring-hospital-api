package com.hospital_api.domain.employee.validations.receptionist;

import com.hospital_api.dto.employee.receptionist.ReceptionistRequestDTO;

public interface ReceptionistValidationHandler {
    void validate(ReceptionistRequestDTO data);
}
