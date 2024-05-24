package com.hospital_api.dto.employee.medic;

import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.employee.medic.MedicSpecialty;

public record MedicDetailDTO(
        Long id,
        String name,
        String cpf,
        String crm,
        MedicSpecialty specialty,
        String login
) {
    public MedicDetailDTO(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getCpf(),
                medic.getCrm(), medic.getSpecialty(), medic.getUser().getLogin());
    }
}
