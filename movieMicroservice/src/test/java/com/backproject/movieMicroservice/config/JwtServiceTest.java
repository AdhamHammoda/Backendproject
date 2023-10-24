package com.backproject.movieMicroservice.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class JwtServiceTest {
    @InjectMocks
    private  JwtService jwtService;

    private static final String SECRETKEY="D38CF5EF946A711F74C7343C9FFB45DCE524ABC1C812D15322A368AF51";


    @Mock
    private UserDetails userDetails;

    @Test
    void testGenerateTokenSuccessfully()
    {
        String token=jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    void testExtractExpirationSuccessfully()
    {
        String token=jwtService.generateToken(userDetails);
        Date expiration=jwtService.extractExpiration(token);
        assertNotNull(expiration);
    }



    @Test
    void generateExpiredToken()
    {
        Map<String ,Object> extraClaims= new HashMap<>();
        String token= Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()-10000))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRETKEY)), SignatureAlgorithm.HS256)
                .compact();
        assertThrows(ExpiredJwtException.class, () -> {
            jwtService.isTokenValid(token);
        });
    }





}
