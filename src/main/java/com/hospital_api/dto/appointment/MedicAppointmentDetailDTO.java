package com.hospital_api.dto.appointment;

import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.employee.medic.MedicSpecialty;

public record MedicAppointmentDetailDTO(
        Long id,
        String name,
        String crm,
        MedicSpecialty specialty
) {
    public MedicAppointmentDetailDTO(Medic medic) {
        this(medic.getId(), medic.getName(),
                medic.getCrm(), medic.getSpecialty());
    }
}
