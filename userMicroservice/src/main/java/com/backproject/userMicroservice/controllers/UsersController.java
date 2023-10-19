package com.backproject.userMicroservice.controllers;

import com.backproject.userMicroservice.models.AuthenticationRequest;
import com.backproject.userMicroservice.models.AuthenticationResponse;
import com.backproject.userMicroservice.models.RegisterRequest;
import com.backproject.userMicroservice.models.User;
import com.backproject.userMicroservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private final UserService userService;

    @GetMapping(value = "/get-user")
    public List<User> getUsers()  {

        return userService.getUsersData();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> addUser(
            @RequestBody RegisterRequest request)
    {
        AuthenticationResponse auth=userService.register(request);
        if(auth.getToken().equals(""))
        {
            return new ResponseEntity<AuthenticationResponse>(HttpStatus.BAD_REQUEST);
        }
        else
        {
            return ResponseEntity.ok(userService.register(request));
        }
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> signIn(
            @RequestBody AuthenticationRequest request
            )
    {
        return ResponseEntity.ok(userService.authenticate(request));
    }

}
