package com.hospital_api.dto.pacient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacientEditDTO (
        @NotNull
        Long id,
        @NotBlank
        String name
){
}
