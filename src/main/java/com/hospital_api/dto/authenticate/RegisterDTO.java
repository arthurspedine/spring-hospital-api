package com.hospital_api.dto.authenticate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hospital_api.domain.user.UserRole;

@JsonIgnoreProperties(ignoreUnknown = false)
public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
