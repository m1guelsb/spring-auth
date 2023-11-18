package com.m1guelsb.springauth.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignInDto(
    @NotNull @NotBlank String login,
    @NotNull @NotBlank String password) {
}
