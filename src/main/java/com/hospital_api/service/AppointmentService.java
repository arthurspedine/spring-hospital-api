package com.hospital_api.service;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.pacient.Pacient;
import com.hospital_api.dto.appointment.AppointmentRequestDTO;
import com.hospital_api.repository.AppointmentRepository;
import com.hospital_api.repository.MedicRepository;
import com.hospital_api.repository.PacientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment scheduleAppointment(AppointmentRequestDTO data) {
        Pacient pacient = pacientRepository.findById(data.pacientId()).orElseThrow(() -> new EntityNotFoundException("Pacient not found!"));
        List<Medic> medics = new ArrayList<>();
        data.medicId().forEach(m -> {
            Optional<Medic> optionalMedic = medicRepository.findById(m);
            optionalMedic.ifPresent(medics::add);
        });
        if (medics.isEmpty())
            throw new ValidationException("Appointment can't be created without valid medics!");

        Appointment appointment = new Appointment();
        appointment.setPacient(pacient);
        appointment.setMedics(medics);
        appointment.setDateTime(data.appointmentDate());
        appointment.setReason(data.reason());

        appointmentRepository.save(appointment);

        return appointment;
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Appointment not found!"));
    }
}
