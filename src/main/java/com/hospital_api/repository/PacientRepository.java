package com.hospital_api.repository;

import com.hospital_api.domain.pacient.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
}
