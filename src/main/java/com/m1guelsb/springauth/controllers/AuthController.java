package com.m1guelsb.springauth.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m1guelsb.springauth.config.auth.TokenProvider;
import com.m1guelsb.springauth.dtos.SignInDto;
import com.m1guelsb.springauth.dtos.SignUpDto;
import com.m1guelsb.springauth.dtos.SignedDto;
import com.m1guelsb.springauth.entities.User;
import com.m1guelsb.springauth.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AuthService service;
  @Autowired
  private TokenProvider tokenService;

  @PostMapping("/signup")
  public ResponseEntity<SignedDto> register(@RequestBody @Valid SignUpDto data) {
    var userDetails = service.signUp(data);

    var accessToken = tokenService.generateAccessToken((User) userDetails);
    var refreshToken = tokenService.generateRefreshToken((User) userDetails);

    return ResponseEntity.ok(new SignedDto(accessToken, refreshToken, userDetails.getUsername()));

  }

  @PostMapping("/signin")
  public ResponseEntity<SignedDto> login(@RequestBody @Valid SignInDto data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

    var authUser = authenticationManager.authenticate(usernamePassword);

    var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
    var refreshToken = tokenService.generateRefreshToken((User) authUser.getPrincipal());

    return ResponseEntity.ok(new SignedDto(accessToken, refreshToken, authUser.getName()));
  }

}