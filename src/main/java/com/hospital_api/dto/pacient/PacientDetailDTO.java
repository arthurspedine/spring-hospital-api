package com.hospital_api.dto.pacient;

import com.hospital_api.domain.pacient.Pacient;

import java.time.LocalDate;

public record PacientDetailDTO(
        String name,
        String cpf,
        LocalDate birthDate,
        String bloodType,
        String login
) {
    public PacientDetailDTO(Pacient pacient) {
        this(pacient.getName(), pacient.getCpf(), pacient.getBirthDate(), pacient.getBloodType(), pacient.getUser().getLogin());
    }
}
