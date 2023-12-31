package com.backproject.userMicroservice.services;

import com.backproject.userMicroservice.config.JwtService;
import com.backproject.userMicroservice.dao.UserDAO;
import com.backproject.userMicroservice.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<User> getUsersData()
    {
        List<User> users= userDAO.findAll();
        return users;
    }
    public AuthenticationResponse register(RegisterRequest request)
    {
        if(userDAO.findByUsername(request.getUsername()).isPresent())
        {
            return AuthenticationResponse.builder().token("").build();
        }
        User user=User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userDAO.save(user);
        String jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()
                )
        );

        User user=userDAO.findByUsername(request.getUsername()).orElseThrow();
        System.out.println(user);
        String jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}