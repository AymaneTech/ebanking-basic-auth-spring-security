package com.wora.ebanking.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponseDTO(@NotNull Long id,
                              @NotBlank String email,
                              @NotBlank String firstName,
                              @NotBlank String lastName,
                              @NotNull RoleResponseDTO role
) {
}
