package com.wora.ebanking.service;

import com.wora.ebanking.domain.Role;
import com.wora.ebanking.exception.EntityNotFoundException;
import com.wora.ebanking.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultRoleService implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("role with id %s not found", name)));
    }
}
