package com.wora.ebanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name", "last_name"})
})

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class User {
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
}
