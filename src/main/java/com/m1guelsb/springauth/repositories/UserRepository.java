package com.m1guelsb.springauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.m1guelsb.springauth.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByLogin(String login);
}
