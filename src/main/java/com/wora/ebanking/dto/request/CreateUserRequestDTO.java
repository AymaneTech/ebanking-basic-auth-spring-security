package com.wora.ebanking.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(@NotBlank String email,
                                   @NotBlank String firstName,
                                   @NotBlank String lastName,
                                   @NotBlank String password
) {
}
