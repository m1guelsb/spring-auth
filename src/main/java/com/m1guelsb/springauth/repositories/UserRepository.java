package com.m1guelsb.springauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m1guelsb.springauth.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

}
