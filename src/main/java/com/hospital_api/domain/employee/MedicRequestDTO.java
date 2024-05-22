package com.hospital_api.domain.employee;

import com.hospital_api.domain.employee.medic.MedicSpecialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicRequestDTO(

        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        MedicSpecialty specialty,

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
