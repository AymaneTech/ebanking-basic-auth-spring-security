package com.wora.ebanking.repository;

import com.wora.ebanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
