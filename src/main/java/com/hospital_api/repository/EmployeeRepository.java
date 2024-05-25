package com.hospital_api.repository;

import com.hospital_api.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCpf(String cpf);

    @Query("""
            select e
            from Employees e
            join e.user u
            where u.login = :login
            """)
    Optional<Employee> existsUserByLogin(String login);

}
