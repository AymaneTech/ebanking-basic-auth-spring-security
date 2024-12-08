package com.wora.ebanking.service;

import com.wora.ebanking.domain.Role;

public interface RoleService {
    Role findEntityByName(String name);
}
