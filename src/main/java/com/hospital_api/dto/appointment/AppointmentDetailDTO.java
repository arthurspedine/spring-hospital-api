package com.hospital_api.dto.appointment;

import com.hospital_api.domain.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AppointmentDetailDTO(
        Long id,
        PacientAppointmentDetailDTO pacient,
        List<MedicAppointmentDetailDTO> medics,
        LocalDateTime appointmentDate,
        String reason
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(appointment.getId(),
                new PacientAppointmentDetailDTO(appointment.getPacient()),
                appointment.getMedics().stream().map(MedicAppointmentDetailDTO::new).collect(Collectors.toList()),
                appointment.getDateTime(),
                appointment.getReason()
                );
    }
}
