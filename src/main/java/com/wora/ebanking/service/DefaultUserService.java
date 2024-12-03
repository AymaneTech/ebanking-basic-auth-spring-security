package com.wora.ebanking.service;

import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.ChangePasswordRequestDTO;
import com.wora.ebanking.dto.request.UpdateUserRequestDTO;
import com.wora.ebanking.dto.response.UserResponseDTO;
import com.wora.ebanking.exception.EntityNotFoundException;
import com.wora.ebanking.exception.IncorrectPasswordException;
import com.wora.ebanking.mapper.UserMapper;
import com.wora.ebanking.repository.RoleRepository;
import com.wora.ebanking.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class DefaultUserService implements UserService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    // TODO: still need to add registration method here

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s not found!", email)));
    }

    public UserResponseDTO findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));
    }

    public User findEntityByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));
    }

    public UserResponseDTO update(String username, UpdateUserRequestDTO request) {
        User user = findEntityByUsername(username)
                .email(request.email())
                .username(request.username())
                .firstName(request.firstName())
                .lastName(request.lastName());

        return mapper.toResponseDTO(user);
    }

    public void changePassword(String username, ChangePasswordRequestDTO request) {
        User user = findEntityByUsername(username);
        if (!passwordEncoder.matches(request.oldPassword(), user.password()))
            throw new IncorrectPasswordException("Given Incorrect Password!");

        user.password(passwordEncoder.encode(request.newPassword()));
    }

    public void delete(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));

        repository.delete(user);
    }
}
