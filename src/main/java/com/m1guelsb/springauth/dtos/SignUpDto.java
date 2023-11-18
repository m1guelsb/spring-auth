package com.m1guelsb.springauth.dtos;

import com.m1guelsb.springauth.dtos.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpDto(
    @NotNull @NotBlank String login,
    @NotNull @NotBlank String password,
    @NotNull @NotBlank UserRole role) {
}
