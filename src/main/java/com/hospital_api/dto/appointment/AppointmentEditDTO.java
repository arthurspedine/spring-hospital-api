package com.hospital_api.dto.appointment;

import java.util.List;

public record AppointmentEditDTO(
        Long id,
        List<Long> medics
) {
}
