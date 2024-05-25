package com.hospital_api.controller;

import com.hospital_api.domain.employee.receptionist.Receptionist;
import com.hospital_api.domain.employee.receptionist.ReceptionistRequestDTO;
import com.hospital_api.dto.employee.receptionist.ReceptionistDetailDTO;
import com.hospital_api.service.ReceptionistService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/employee/receptionist")
public class ReceptionistController {

    @Autowired
    private ReceptionistService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReceptionistDetailDTO> createReceptionist(@RequestBody @Valid ReceptionistRequestDTO data, UriComponentsBuilder uriBuilder) {
        Receptionist receptionist = service.saveReceptionist(data);

        URI uri = uriBuilder.path("/employee/receptionist/{id}").buildAndExpand(receptionist.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReceptionistDetailDTO(receptionist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionistDetailDTO> medicDetail(@PathVariable Long id) {
        return ResponseEntity.ok(new ReceptionistDetailDTO(service.getReceptionistById(id)));
    }

}