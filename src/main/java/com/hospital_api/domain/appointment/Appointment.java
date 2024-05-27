package com.hospital_api.domain.appointment;

import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.domain.pacient.Pacient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "appointments")
@Entity(name = "Appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", referencedColumnName = "id", nullable = false)
    private Pacient pacient;

    @ManyToMany
    @JoinTable(
            name = "medic_appointments",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "medic_id")
    )
    private Set<Medic> medics = new HashSet<>();

    private LocalDateTime dateTime;

    private String reason;

}
