package com.hospital_api.dto.authenticate;

public record LoginRequestDTO(
        String login,
        String password
) {
}
