package com.hospital_api.dto.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelAppointmentDTO(
        @NotNull
        Long id,
        @NotBlank
        String reason
) {
}
