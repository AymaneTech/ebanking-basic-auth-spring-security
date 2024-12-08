package com.wora.ebanking.seeder;

import com.wora.ebanking.domain.Role;
import com.wora.ebanking.domain.User;
import com.wora.ebanking.exception.EntityNotFoundException;
import com.wora.ebanking.repository.RoleRepository;
import com.wora.ebanking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {
    private static final String DEFAULT_PASSWORD = "password";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final RoleRepository roleRepository;

    @Bean
    CommandLineRunner createRoles() {
        return args -> {
            if (roleRepository.count() != 0)
                return;

            Stream.of("ROLE_USER", "ROLE_ADMIN")
                    .map(Role::new)
                    .forEach(roleRepository::save);

        };
    }

    @Bean
    CommandLineRunner createAdmin(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() != 0)
                return;

            Role role = roleRepository.findByName(ADMIN_ROLE)
                    .orElseThrow(() -> new EntityNotFoundException("admin role not found"));

            User user = User.builder()
                    .email("admin@gmail.com")
                    .firstName("admin")
                    .lastName("El Maini")
                    .password(passwordEncoder.encode(DEFAULT_PASSWORD))
                    .role(role)
                    .build();

            repository.save(user);
        };
    }
}
