package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.model.User;
import com.eindopdracht.eindopdracht_forster.dto.UserDto;
import com.eindopdracht.eindopdracht_forster.model.Role;
import com.eindopdracht.eindopdracht_forster.repository.RoleRepository;
import com.eindopdracht.eindopdracht_forster.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public String createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        Set<Role> userRoles = newUser.getRoles();
        for (String roleName : userDto.roles) {
            Optional<Role> or = roleRepository.findById("ROLE_" + roleName);
            or.ifPresent(userRoles::add);
        }

        userRepository.save(newUser);
        return "Done";
    }
}