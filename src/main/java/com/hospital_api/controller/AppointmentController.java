package com.hospital_api.controller;

import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.dto.appointment.AppointmentDetailDTO;
import com.hospital_api.dto.appointment.AppointmentRequestDTO;
import com.hospital_api.service.AppointmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailDTO> scheduleAppointment(@RequestBody @Valid AppointmentRequestDTO data, UriComponentsBuilder uriBuilder) {
        Appointment appointment = service.scheduleAppointment(data);

        URI uri = uriBuilder.path("/appointment/{id}").buildAndExpand(appointment.getId()).toUri();

        return ResponseEntity.created(uri).body(new AppointmentDetailDTO(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailDTO> scheduleDetail(
            @PathVariable Long id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Appointment appointment = service.getAppointment(id, authHeader);
        return appointment != null ?
                ResponseEntity.ok(new AppointmentDetailDTO(appointment)) :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
