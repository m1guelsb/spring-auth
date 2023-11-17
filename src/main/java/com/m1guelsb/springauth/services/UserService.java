package com.m1guelsb.springauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.m1guelsb.springauth.repositories.UserRepository;

public class UserService implements UserDetailsService {

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    var user = repository.findByUsername(username);

    if (user == null)
      throw new UsernameNotFoundException("User not found!");

    return user;
  }
}