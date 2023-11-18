package com.m1guelsb.springauth.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.m1guelsb.springauth.entities.User;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenProvider {
  @Value("${security.jwt.token.secret-key}")
  private String JWT_SECRET;
  private String JWT_ISSUER = "auth-api";

  public String generateAccessToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
      return JWT.create()
          .withIssuer(JWT_ISSUER)
          .withSubject(user.getUsername())
          .withClaim("username", user.getUsername())
          .withExpiresAt(genAccessExpirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public String generateRefreshToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
      return JWT.create()
          .withIssuer(JWT_ISSUER)
          .withSubject(user.getUsername())
          .withClaim("username", user.getUsername())
          .withExpiresAt(genRefreshExpirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error while generating token", exception);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
      return JWT.require(algorithm)
          .withIssuer(JWT_ISSUER)
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException exception) {
      return "";
    }
  }

  private Instant genAccessExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  private Instant genRefreshExpirationDate() {
    return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
  }
}