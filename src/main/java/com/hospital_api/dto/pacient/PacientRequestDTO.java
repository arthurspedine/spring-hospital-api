package com.hospital_api.dto.pacient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PacientRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull
        LocalDate date,

        @NotBlank
        String bloodType,

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
