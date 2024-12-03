package com.wora.ebanking.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleResponseDTO(@NotNull Long id,
                              @NotBlank String name) {
}
