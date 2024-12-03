package com.wora.ebanking.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(@NotBlank String oldPassword,
                                       @NotBlank String newPassword
) {
}
