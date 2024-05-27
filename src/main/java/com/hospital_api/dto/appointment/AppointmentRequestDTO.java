package com.hospital_api.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record AppointmentRequestDTO(

        Long pacientId,

        List<Long> medicId,
        @NotNull
        @Future
        LocalDateTime appointmentDate,
        @NotBlank
        String reason
) {
}
