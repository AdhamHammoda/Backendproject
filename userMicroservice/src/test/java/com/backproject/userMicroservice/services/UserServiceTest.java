package com.backproject.userMicroservice.services;

import com.backproject.userMicroservice.config.JwtService;
import com.backproject.userMicroservice.dao.UserDAO;
import com.backproject.userMicroservice.models.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RequiredArgsConstructor
@ContextConfiguration(classes = TestConfiguration.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private  UserDAO userDAO;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;


    @Test
    void registerNewUserSuccessfully()
    {
        RegisterRequest request=new RegisterRequest("a.hammoda","pass");
        User user=User.builder().username(request.getUsername())
                .password("1234").role(Role.USER).build();

        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("1234");
        Mockito.when(userDAO.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(jwtService.generateToken(user)).thenReturn("token");
        AuthenticationResponse response=userService.register(request);
        assertEquals("token",response.getToken());
    }

    @Test
    void authenticateUserSuccessfully()
    {
        AuthenticationRequest request=new AuthenticationRequest("a.hammoda","pass");
        User user=User.builder().username(request.getUsername())
                .password("1234").role(Role.USER).build();

        Mockito.when(userDAO.findByUsername(request.getUsername())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateToken(user)).thenReturn("token");
        AuthenticationResponse response=userService.authenticate(request);
        assertEquals("token",response.getToken());
    }

}
