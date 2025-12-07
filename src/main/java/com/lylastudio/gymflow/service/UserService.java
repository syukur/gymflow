package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.AuthRequest;
import com.lylastudio.gymflow.dto.GoogleAuthRequest;
import com.lylastudio.gymflow.entity.MUser;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.security.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final MUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new AppUser(user);
    }

    public Optional<UserDetails> loadByEmail(String email) {
        return userRepository.findByEmail(email).map(AppUser::new);
    }

    @Transactional
    public UserDetails registerGoogleUser( String email, String name, String sub) {
        MUser user = new MUser();
        user.setUsername(name);
        user.setPassword("");
        user.setSub(sub);
        user.setAuthProvider("GOOGLE");
        user.setEmail(email);
        userRepository.save(user);

        return new AppUser(user);
    }
}
