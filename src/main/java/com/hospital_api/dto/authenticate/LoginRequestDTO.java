package com.hospital_api.dto.authenticate;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
