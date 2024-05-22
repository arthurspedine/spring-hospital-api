package com.hospital_api.domain.employee.receptionist;

import com.hospital_api.domain.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "receptionists")
@Entity(name = "Receptionists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receptionist extends Employee {
    @Enumerated(EnumType.STRING)
    private ShiftType shift;
}
