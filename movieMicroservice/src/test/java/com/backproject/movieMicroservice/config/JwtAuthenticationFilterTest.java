package com.backproject.movieMicroservice.config;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    FilterChain filterChain;



    @Test
    void doFilterWithoutAuthorization() throws Exception
    {
        Mockito.when(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
        jwtAuthenticationFilter.doFilterInternal(httpServletRequest,httpServletResponse,filterChain);
        Mockito.verify(filterChain).doFilter(httpServletRequest,httpServletResponse);
    }






}
