package com.backproject.userMicroservice.config;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
public class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterChain filterChain;

    @Test
    void auth() throws Exception
    {
        Mockito.when(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
        jwtAuthenticationFilter.doFilterInternal(httpServletRequest,httpServletResponse,filterChain);
        Mockito.verify(filterChain).doFilter(httpServletRequest,httpServletResponse);
    }
}
