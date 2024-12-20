package com.wora.ebanking.controller;

import com.wora.ebanking.dto.request.CreateUserRequestDTO;
import com.wora.ebanking.dto.response.UserResponseDTO;
import com.wora.ebanking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("auth/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid CreateUserRequestDTO request) {
        UserResponseDTO user = service.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) {
        UserResponseDTO user = service.findByUsername(username);
        return ResponseEntity.ok(user);
    }
}
