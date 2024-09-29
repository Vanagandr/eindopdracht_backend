package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.AuthDto;
import com.eindopdracht.eindopdracht_forster.exception.AuthenticationExceptionCustom;
import com.eindopdracht.eindopdracht_forster.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String signIn(AuthDto authDto) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(authDto.username, authDto.password);

        try {
            Authentication auth = authManager.authenticate(up);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            return jwtService.generateToken(userDetails);
        } catch (AuthenticationException ex) {
            throw new AuthenticationExceptionCustom("Onjuiste username of wachtwoord");

        }
    }
}