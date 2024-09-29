package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.UserDto;
import com.eindopdracht.eindopdracht_forster.model.Role;
import com.eindopdracht.eindopdracht_forster.model.User;
import com.eindopdracht.eindopdracht_forster.repository.RoleRepository;
import com.eindopdracht.eindopdracht_forster.repository.UserRepository;
import com.eindopdracht.eindopdracht_forster.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}