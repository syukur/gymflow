package com.lylastudio.gymflow.config;

import com.lylastudio.gymflow.repository.MRoleRepository;
import com.lylastudio.gymflow.security.CustomAuthenticationEntryPoint;
import com.lylastudio.gymflow.security.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final MRoleRepository roleRepository;


    // Daftar endpoint yang diizinkan untuk diakses publik
    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/api/hello",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(WHITE_LIST_URL).permitAll()
//                        .anyRequest().authenticated()
//                )
                .authorizeHttpRequests(req -> {
                    // Izinkan akses publik ke white list
                    req.requestMatchers(WHITE_LIST_URL).permitAll();

                    // Muat aturan dinamis dari database
//                    roleRepository.findAll().forEach(role ->
//                            role.getEndpoints().forEach(endpoint ->
//                                    req.requestMatchers(endpoint.getEndpoint().getEnpoint()).hasRole(role.getName())
//                            )
//                    );

                    roleRepository.findAll().forEach(role ->{
                                log.info("Role: {},{}",role.getName(), role.getId());
                                role.getRoleEnpoints().forEach(roleEnpoint ->{
                                    log.info("endpoint: {}", roleEnpoint.getEndpoint().getEnpoint());
                                });
                            }
                            //log.info("Role: " + role.getName());

//                            role.getEndpoints().forEach(endpoint ->
//                                    req.requestMatchers(endpoint.getEndpoint().getEnpoint()).hasRole(role.getName())
//                            )
                    );

                    // Semua request lain harus diautentikasi
                    req.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(customAuthenticationEntryPoint)) // Daftarkan entry point
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
