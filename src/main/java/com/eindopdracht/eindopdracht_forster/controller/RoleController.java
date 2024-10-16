package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.RoleDto;
import com.eindopdracht.eindopdracht_forster.model.Role;
import com.eindopdracht.eindopdracht_forster.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {

    private final RoleRepository repos;

    //Gets roles from database
    public RoleController(RoleRepository repos) {
        this.repos = repos;
    }
    @GetMapping("/roles")
    public List<RoleDto> getRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role r : repos.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.rolename = r.getRolename();
            roleDtos.add(rdto);
        }
        return roleDtos;
    }
}
