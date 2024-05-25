package com.hospital_api.domain.employee.receptionist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ReceptionistRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull
        ShiftType shift,

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
