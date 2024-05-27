package com.hospital_api.dto.appointment;

import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.dto.employee.medic.MedicDetailDTO;
import com.hospital_api.dto.pacient.PacientDetailDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AppointmentDetailDTO(
        PacientDetailDTO pacient,
        List<MedicDetailDTO> medics,
        LocalDateTime appointmentDate,
        String reason
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(new PacientDetailDTO(appointment.getPacient()),
                appointment.getMedics().stream().map(MedicDetailDTO::new).collect(Collectors.toList()),
                appointment.getDateTime(),
                appointment.getReason()
                );
    }
}
