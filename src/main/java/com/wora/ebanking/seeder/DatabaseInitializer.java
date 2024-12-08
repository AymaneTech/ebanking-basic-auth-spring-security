package com.wora.ebanking.seeder;

import com.wora.ebanking.domain.Role;
import com.wora.ebanking.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DatabaseInitializer {

    @Bean
    CommandLineRunner createRoles(RoleRepository repository) {
       return args -> {
           if (repository.count() == 0)
               return;

           Stream.of("ROLE_USER", "ROLE_ADMIN")
                   .map(Role::new)
                   .forEach(repository::save);
       };
    }
}
