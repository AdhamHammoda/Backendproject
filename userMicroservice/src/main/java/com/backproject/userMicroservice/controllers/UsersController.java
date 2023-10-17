package com.backproject.userMicroservice.controllers;

import com.backproject.userMicroservice.models.AuthenticationRequest;
import com.backproject.userMicroservice.models.AuthenticationResponse;
import com.backproject.userMicroservice.models.RegisterRequest;
import com.backproject.userMicroservice.models.User;
import com.backproject.userMicroservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get-user")
    public List<User> getUsers()  {
        return userService.getUsersData();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> addUser(
            @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(userService.register(request));
    }


    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> signIn(
            @RequestBody AuthenticationRequest request
            )
    {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
