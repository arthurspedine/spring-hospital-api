package com.hospital_api.dto.employee.receptionist;

import com.hospital_api.domain.employee.receptionist.Receptionist;
import com.hospital_api.domain.employee.receptionist.ShiftType;

public record ReceptionistDetailDTO(
        Long id,
        String name,
        String cpf,
        ShiftType shift,
        String login
) {
    public ReceptionistDetailDTO(Receptionist receptionist) {
        this(receptionist.getId(), receptionist.getName(), receptionist.getCpf(),
                receptionist.getShift(), receptionist.getUser().getLogin());
    }
}
