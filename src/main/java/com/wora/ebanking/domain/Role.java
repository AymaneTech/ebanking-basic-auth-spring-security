package com.wora.ebanking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "roles")

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
