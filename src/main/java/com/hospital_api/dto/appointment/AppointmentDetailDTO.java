package com.hospital_api.dto.appointment;

import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.dto.pacient.PacientDetailDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AppointmentDetailDTO(
        PacientDetailDTO pacient,
        List<MedicAppointmentDetailDTO> medics,
        LocalDateTime appointmentDate,
        String reason
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(new PacientDetailDTO(appointment.getPacient()),
                appointment.getMedics().stream().map(MedicAppointmentDetailDTO::new).collect(Collectors.toList()),
                appointment.getDateTime(),
                appointment.getReason()
                );
    }
}
