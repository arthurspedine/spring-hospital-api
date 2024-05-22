package com.hospital_api.domain.employee;

import com.hospital_api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "employees")
@Entity(name = "Employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 14, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
