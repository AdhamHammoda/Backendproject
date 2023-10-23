package com.backproject.userMicroservice.config;


import com.backproject.userMicroservice.models.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthenticationFilter {
    @Mock
    private JwtService jwtService;
    @Mock
    private UserDetailsService userDetailsService;
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

}
