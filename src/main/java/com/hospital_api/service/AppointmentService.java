package com.hospital_api.service;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.pacient.Pacient;
import com.hospital_api.dto.appointment.AppointmentEditDTO;
import com.hospital_api.dto.appointment.AppointmentRequestDTO;
import com.hospital_api.infra.security.TokenService;
import com.hospital_api.repository.AppointmentRepository;
import com.hospital_api.repository.MedicRepository;
import com.hospital_api.repository.PacientRepository;
import com.hospital_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository userRepository;

    private final String notFound = "Appointment not found!";

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

    public Appointment getAppointment(Long id, String header) {
        String login = service.getSubject(header.replace("Bearer ", ""));

        UserDetails user = userRepository.findByLogin(login);

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(notFound));

        // CHECK IF IS ADMIN
        boolean isAdmin = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);
        if (appointment.getPacient().getUser().getLogin().equals(user.getUsername()) ||
            isAdmin) {
            return appointment;
        }
        return null;
    }

    public Appointment editAppointment(AppointmentEditDTO data) {
        Appointment appointment = appointmentRepository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException(notFound));
        List<Medic> medics = new ArrayList<>();
        data.medics().forEach(m -> {
            Optional<Medic> medic = medicRepository.findById(m);
            medic.ifPresent(medics::add);
        });
        medics.forEach(m -> {
            if (!appointment.getMedics().contains(m)) {
                appointment.getMedics().add(m);
            }
        });
        return appointment;
    }
}
