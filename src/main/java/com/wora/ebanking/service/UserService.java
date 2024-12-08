package com.wora.ebanking.service;

import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.ChangePasswordRequestDTO;
import com.wora.ebanking.dto.request.CreateUserRequestDTO;
import com.wora.ebanking.dto.request.UpdateUserRequestDTO;
import com.wora.ebanking.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO register(CreateUserRequestDTO request);

    List<UserResponseDTO> findAll();

    User findByEmail(String email);

    UserResponseDTO findByUsername(String username);

    User findEntityByUsername(String username);

    UserResponseDTO update(String username, UpdateUserRequestDTO request);

    void changePassword(String username, ChangePasswordRequestDTO request);

    void delete(String username);
}
