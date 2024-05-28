package com.hospital_api.dto.appointment;

import com.hospital_api.domain.pacient.Pacient;

import java.time.LocalDate;

public record PacientAppointmentDetailDTO(
        Long id,
        String name,
        String cpf,
        LocalDate birthDate,
        String bloodType
) {
    public PacientAppointmentDetailDTO(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getCpf(),
                pacient.getBirthDate(), pacient.getBloodType());
    }
}
