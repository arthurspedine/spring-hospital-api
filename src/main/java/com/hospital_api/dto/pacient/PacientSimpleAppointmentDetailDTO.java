package com.hospital_api.dto.pacient;

import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.dto.appointment.MedicAppointmentDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

public record PacientSimpleAppointmentDetailDTO(
        List<MedicAppointmentDetailDTO> medics,
        LocalDateTime date,
        String reason
) {
    public PacientSimpleAppointmentDetailDTO(Appointment a) {
        this(a.getMedics().stream().map(MedicAppointmentDetailDTO::new).toList(), a.getDateTime(), a.getReason());
    }
}
