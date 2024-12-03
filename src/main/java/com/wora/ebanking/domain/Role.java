package com.wora.ebanking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "roles")

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
