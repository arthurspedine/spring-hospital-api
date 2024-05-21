package com.hospital_api.config;

import com.hospital_api.domain.user.User;
import com.hospital_api.domain.user.UserRole;
import com.hospital_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserDetails userAdmin = repository.findByLogin("admin");

        if (userAdmin == null) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword(encoder.encode("123"));
            user.setRole(UserRole.ADMIN);
            repository.save(user);
        } else {
            System.out.println("Admin já existe");
        }

    }
}
