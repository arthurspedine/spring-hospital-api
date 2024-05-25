package com.hospital_api.controller;

import com.hospital_api.domain.employee.medic.MedicRequestDTO;
import com.hospital_api.domain.employee.medic.Medic;
import com.hospital_api.dto.employee.medic.MedicDetailDTO;
import com.hospital_api.service.MedicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee/medic")
@SecurityRequirement(name = "bearer-key")
public class MedicController {

    @Autowired
    private MedicService service;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicDetailDTO> createMedic(@RequestBody @Valid MedicRequestDTO data, UriComponentsBuilder uriBuilder) {
        Medic medic = service.saveMedic(data);

        URI uri = uriBuilder.path("/employee/medic/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicDetailDTO(medic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDetailDTO> medicDetail(@PathVariable Long id) {
        return ResponseEntity.ok(new MedicDetailDTO(service.getMedicById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<MedicDetailDTO>> listAllMedics(@PageableDefault(sort = "name") Pageable page) {
        return ResponseEntity.ok(service.getAllMedics(page));
    }
}
