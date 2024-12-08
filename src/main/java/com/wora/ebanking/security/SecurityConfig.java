package com.wora.ebanking.security;

import com.wora.ebanking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/api/auth/register",
            "/api/v1/notices",
            "/api/v1/contact"
    };

    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .anyRequest().hasRole("USER")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::findEntityByUsername;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessEventListener() {
        return event -> {
            Authentication auth = event.getAuthentication();
            log.info("user {} logged in as {} !",
                    auth.getName(),
                    auth.getClass().getSimpleName()
            );
        };
    }
}
