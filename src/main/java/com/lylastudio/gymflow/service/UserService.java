package com.lylastudio.gymflow.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Optional<UserDetails> loadByEmail(String email);
    UserDetails registerGoogleUser( String email, String name, String sub);
}
