package com.hospital_api.repository;

import com.hospital_api.domain.employee.receptionist.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
}
