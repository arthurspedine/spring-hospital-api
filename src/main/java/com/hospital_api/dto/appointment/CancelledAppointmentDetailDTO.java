package com.hospital_api.dto.appointment;

import com.hospital_api.domain.appointment.Appointment;

public record CancelledAppointmentDetailDTO (
        String cancellationReason,
        AppointmentDetailDTO appointment
) {
    public CancelledAppointmentDetailDTO(Appointment appointment) {
        this(appointment.getCancellationReason(), new AppointmentDetailDTO(appointment));
    }
}
