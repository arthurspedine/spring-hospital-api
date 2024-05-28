package com.hospital_api.dto.employee.receptionist;

import com.hospital_api.domain.employee.receptionist.ShiftType;
import jakarta.validation.constraints.NotNull;

public record ReceptionistEditDTO(
        @NotNull
        Long id,
        String name,
        ShiftType shiftType
) {
}
