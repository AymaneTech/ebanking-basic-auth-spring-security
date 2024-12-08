package com.wora.ebanking.service;

import com.wora.ebanking.domain.Role;
import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.ChangePasswordRequestDTO;
import com.wora.ebanking.dto.request.CreateUserRequestDTO;
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

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
class DefaultUserService implements UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserResponseDTO register(CreateUserRequestDTO request) {
        final Role role = roleService.findEntityByName(DEFAULT_ROLE);
        final User user = mapper.toEntity(request)
                .password(passwordEncoder.encode(request.password()))
                .role(role);

        return mapper.toResponseDTO(user);
    }

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

    public void changeRole(String username, String name) {
        final User user = findEntityByUsername(username);
        final Role role = roleService.findEntityByName(name);

        user.role(role);
    }

    public void delete(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));

        repository.delete(user);
    }

    public User findEntityByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));
    }
}
