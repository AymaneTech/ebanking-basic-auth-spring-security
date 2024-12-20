package com.wora.ebanking.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDTO(@NotBlank String email,
                                   @NotBlank String firstName,
                                   @NotBlank String lastName
) {
}
