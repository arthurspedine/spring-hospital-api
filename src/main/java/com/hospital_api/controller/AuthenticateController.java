package com.hospital_api.controller;

import com.hospital_api.domain.user.User;
import com.hospital_api.dto.authenticate.AuthResponseDTO;
import com.hospital_api.dto.authenticate.LoginRequestDTO;
import com.hospital_api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        String tokenJWT = service.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(tokenJWT));
    }
}
