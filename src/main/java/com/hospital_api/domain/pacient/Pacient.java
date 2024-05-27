package com.hospital_api.domain.pacient;

import com.hospital_api.domain.Person;
import com.hospital_api.domain.appointment.Appointment;
import com.hospital_api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "pacients")
@Entity(name = "Pacients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class Pacient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthDate;

    private String bloodType;

    @OneToMany(mappedBy = "pacient")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
