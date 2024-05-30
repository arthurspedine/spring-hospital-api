package com.hospital_api.controller;

import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.dto.appointment.*;
import com.hospital_api.service.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
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
    public ResponseEntity scheduleDetail(
            @PathVariable Long id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Appointment appointment = service.getAppointment(id, authHeader);
        if (appointment == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        if (appointment.getCancellationReason() != null)
            return ResponseEntity.ok(new CancelledAppointmentDetailDTO(appointment));
        return ResponseEntity.ok(new AppointmentDetailDTO(appointment));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AppointmentDetailDTO> editAppointment(@RequestBody @Valid AppointmentEditDTO data) {
        Appointment appointment = service.editAppointment(data);
        return ResponseEntity.ok(new AppointmentDetailDTO(appointment));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentDetailDTO>> listAllAppointments(@PageableDefault(sort = "id") Pageable page) {
        return ResponseEntity.ok(service.getAllAppointments(page));
    }

    @DeleteMapping
    @Transactional

    public ResponseEntity cancelAppointment(@RequestBody @Valid CancelAppointmentDTO data) {
        service.cancelAppointment(data);
        return ResponseEntity.noContent().build();
    }
}
