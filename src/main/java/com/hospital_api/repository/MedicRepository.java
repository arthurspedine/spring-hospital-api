package com.hospital_api.repository;

import com.hospital_api.domain.employee.medic.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Optional<Medic> findByCrm(String crm);

    @Query("""
    select m from Medics m
    where m.user.login = :login
    """)
    Optional<Medic> existsUserByLogin(String login);
}
