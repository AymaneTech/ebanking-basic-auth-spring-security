package com.wora.ebanking.repository;

import com.wora.ebanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
