package com.hospital_api.dto.pacient;

import com.hospital_api.domain.pacient.Pacient;

import java.time.LocalDate;
import java.util.List;

public record PacientDetailDTO(
        Long id,
        String name,
        String cpf,
        LocalDate birthDate,
        String bloodType,
        String login,
        List<PacientSimpleAppointmentDetailDTO> appointments
) {
    public PacientDetailDTO(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getCpf(), pacient.getBirthDate(),
                pacient.getBloodType(), pacient.getUser().getLogin(),
                pacient.getAppointments().stream().map(PacientSimpleAppointmentDetailDTO::new).toList());
    }
}
