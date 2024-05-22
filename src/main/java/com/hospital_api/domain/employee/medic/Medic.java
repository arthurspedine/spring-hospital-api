package com.hospital_api.domain.employee.medic;

import com.hospital_api.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "medics")
@Entity(name = "Medics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medic extends Employee {

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private MedicSpecialty specialty;

}
