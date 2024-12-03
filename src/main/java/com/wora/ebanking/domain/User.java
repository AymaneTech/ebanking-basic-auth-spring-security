package com.wora.ebanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name", "last_name"})
})

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class User extends UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToOne
    private Role role;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
