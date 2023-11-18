package com.m1guelsb.springauth.dtos;

public record SignedDto(
    String accessToken,
    String refreshToken,
    String username) {
}