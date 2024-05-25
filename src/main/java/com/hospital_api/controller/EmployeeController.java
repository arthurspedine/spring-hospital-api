package com.hospital_api.controller;

import com.hospital_api.domain.employee.EmployeeDetail;
import com.hospital_api.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@SecurityRequirement(name = "bearer-key")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public ResponseEntity<Page<EmployeeDetail>> listAllEmployees(@PageableDefault(sort = "type") Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).map(EmployeeDetail::new));
    }
}
