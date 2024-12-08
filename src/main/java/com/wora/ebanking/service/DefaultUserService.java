package com.wora.ebanking.service;

import com.wora.ebanking.domain.Role;
import com.wora.ebanking.domain.User;
import com.wora.ebanking.dto.request.ChangePasswordRequestDTO;
import com.wora.ebanking.dto.request.CreateUserRequestDTO;
import com.wora.ebanking.dto.request.UpdateUserRequestDTO;
import com.wora.ebanking.dto.response.RoleResponseDTO;
import com.wora.ebanking.dto.response.UserResponseDTO;
import com.wora.ebanking.exception.EntityNotFoundException;
import com.wora.ebanking.exception.IncorrectPasswordException;
import com.wora.ebanking.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
class DefaultUserService implements UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(CreateUserRequestDTO request) {
        final Role role = roleService.findEntityByName(DEFAULT_ROLE);
        final User user = User.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(passwordEncoder.encode(request.password()))
                .role(role)
                .build();

        User savedUser = repository.save(user);
        System.out.println("here is the user entity" + user);

        return this.mapToDto(savedUser);
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email: %s not found!", email)));
    }

    public UserResponseDTO findByUsername(String username) {
        return repository.findByEmail(username)
                .map(this::mapToDto)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));
    }

    public UserResponseDTO update(String username, UpdateUserRequestDTO request) {
        User user = findEntityByUsername(username)
                .setEmail(request.email())
                .setFirstName(request.firstName())
                .setLastName(request.lastName());

        return this.mapToDto(user);
    }

    public void changePassword(String username, ChangePasswordRequestDTO request) {
        User user = findEntityByUsername(username);
        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword()))
            throw new IncorrectPasswordException("Given Incorrect Password!");

        user.setPassword(passwordEncoder.encode(request.newPassword()));
    }

    public void changeRole(String username, String name) {
        final User user = findEntityByUsername(username);
        final Role role = roleService.findEntityByName(name);

        user.setRole(role);
    }

    public void delete(String username) {
        User user = repository.findByEmail(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));

        repository.delete(user);
    }

    public User findEntityByUsername(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> EntityNotFoundException.byUsername(username));
    }

    private UserResponseDTO mapToDto(User user) {
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                new RoleResponseDTO(user.getRole().getId(), user.getRole().getName()));
    }
}
