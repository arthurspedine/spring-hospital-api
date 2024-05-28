package com.hospital_api.controller;

import com.hospital_api.domain.pacient.Pacient;
import com.hospital_api.dto.pacient.PacientDetailDTO;
import com.hospital_api.dto.pacient.PacientEditDTO;
import com.hospital_api.dto.pacient.PacientRequestDTO;
import com.hospital_api.service.PacientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacient")
public class PacientController {

    @Autowired
    private PacientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PacientDetailDTO> createPacient(@RequestBody @Valid PacientRequestDTO data, UriComponentsBuilder uriBuilder) {
        Pacient pacient = service.savePacient(data);

        URI uri = uriBuilder.path("/pacient/{id}").buildAndExpand(pacient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacientDetailDTO(pacient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientDetailDTO> getPacient(@PathVariable Long id) {
        return ResponseEntity.ok(new PacientDetailDTO(service.getPacientById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacientDetailDTO> editPacient(@RequestBody @Valid PacientEditDTO data) {
        Pacient pacient = service.editPacient(data);
        return ResponseEntity.ok(new PacientDetailDTO(pacient));
    }

}
