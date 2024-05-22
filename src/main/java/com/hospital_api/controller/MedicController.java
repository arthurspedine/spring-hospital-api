package com.hospital_api.controller;

import com.hospital_api.domain.employee.MedicRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/medic")
public class MedicController {

    @PostMapping
    @Transactional
    public ResponseEntity createMedic(@RequestBody @Valid MedicRequestDTO data) {
        return ResponseEntity.ok().body(data);
    }
}
