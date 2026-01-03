package com.lylastudio.gymflow.config;

import com.lylastudio.gymflow.entity.TRoleEnpoint;
import com.lylastudio.gymflow.repository.MEnpointRespository;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    //private final MRoleRepository roleRepository;
    private final MEnpointRespository enpointRespository;


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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter, PlatformTransactionManager transactionManager) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> {

                    // Izinkan akses publik ke white list
                    req.requestMatchers(WHITE_LIST_URL).permitAll();

                    req.requestMatchers("/api/companies").hasRole("OWNER");
                    req.requestMatchers("/api/members/register").hasAnyRole("OWNER","CASHIER");
                    req.requestMatchers("/api/members/register").hasRole("CASHIER");


//                    endpoint: /api/members/register, hasRole: CASHIER
//                     endpoint: /api/companies, hasRole: OWNER
//                     endpoint: /api/members/register, hasRole: OWNER
//                    new TransactionTemplate(transactionManager).executeWithoutResult(transactionStatus -> {
//                        // Muat aturan dinamis dari database
//                        roleRepository.findAll().forEach(role ->
//                            role.getRoleEnpoints().forEach(endpoint ->{
//                                req.requestMatchers(endpoint.getEndpoint().getEnpoint()).hasRole(role.getName());
//                                log.info("endpoint: {}, hasRole: {}", endpoint.getEndpoint().getEnpoint(), role.getName() );
//                            }
//
//                            )
//                        );
//                    });

                    new TransactionTemplate(transactionManager).executeWithoutResult(transactionStatus -> {
                        // Muat aturan dinamis dari database
                        enpointRespository.findAll().forEach(enpoint -> {
                            Set<TRoleEnpoint> roleEnpoints = enpoint.getRoleEnpoints();
                            if ( !roleEnpoints.isEmpty() ){
                                if(roleEnpoints.size() > 1){
                                    StringBuilder sb = new StringBuilder();

                                    roleEnpoints.forEach(roleEnpoint -> {
                                        sb.append(roleEnpoint.getRole().getName()).append(",");
                                    });

                                    String hasAnyRole = sb.toString();
                                    hasAnyRole = hasAnyRole.substring(0, hasAnyRole.length() - 1);
                                    req.requestMatchers(enpoint.getEnpoint()).hasRole(hasAnyRole);
                                    log.info("endpoint: {}, hasAnyRole: {}", enpoint.getEnpoint(), hasAnyRole);

                                }else{
                                    req.requestMatchers(enpoint.getEnpoint()).hasRole(roleEnpoints.iterator().next().getRole().getName());
                                    log.info("endpoint: {}, hasRole: {}", enpoint.getEnpoint(), roleEnpoints.iterator().next().getRole().getName());
                                }
                            }
                        });

                    });

                    // Semua request lain harus diautentikasi
                    req.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(customAuthenticationEntryPoint)) // Daftarkan entry point
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
