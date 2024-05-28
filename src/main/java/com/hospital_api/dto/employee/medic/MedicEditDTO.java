package com.hospital_api.dto.employee.medic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicEditDTO(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
