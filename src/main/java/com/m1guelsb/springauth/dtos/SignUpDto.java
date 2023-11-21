package com.m1guelsb.springauth.dtos;

import com.m1guelsb.springauth.enums.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role) {
}
