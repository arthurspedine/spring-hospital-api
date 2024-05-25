package com.hospital_api.controller;

import com.hospital_api.domain.ValidationException;
import com.hospital_api.domain.user.User;
import com.hospital_api.dto.authenticate.LoginRequestDTO;
import com.hospital_api.dto.authenticate.AuthResponseDTO;
import com.hospital_api.dto.authenticate.RegisterDTO;
import com.hospital_api.infra.security.TokenService;
import com.hospital_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        String tokenJWT = service.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(tokenJWT));
    }

    @PostMapping("/register") // just ADMIN role can release this method
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByLogin(data.login()) != null) // valid unique user
            throw new ValidationException("There is already a record with the same Login provided.");

        String encryptedPassword = encoder.encode(data.password());
        User newUser = new User(null, data.login(), encryptedPassword, data.role(), null);

        repository.save(newUser);
        return ResponseEntity.ok().body("User created successfully!");
    }
}
