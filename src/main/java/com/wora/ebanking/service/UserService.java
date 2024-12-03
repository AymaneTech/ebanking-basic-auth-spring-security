package com.wora.ebanking.service;

import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.ChangePasswordRequestDTO;
import com.wora.ebanking.dto.request.UpdateUserRequestDTO;
import com.wora.ebanking.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponseDTO> findAll(Pageable pageable);

    User findByEmail(String email);

    UserResponseDTO findByUsername(String username);

    User findEntityByUsername(String username);

    UserResponseDTO update(String username, UpdateUserRequestDTO request);

    void changePassword(String username, ChangePasswordRequestDTO request)

    void delete(String username);
}
