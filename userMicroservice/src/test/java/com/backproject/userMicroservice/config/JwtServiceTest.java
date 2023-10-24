package com.backproject.userMicroservice.config;

import com.backproject.userMicroservice.models.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class JwtServiceTest {

    @InjectMocks
    private  JwtService jwtService;

    private static final String SECRETKEY="D38CF5EF946A711F74C7343C9FFB45DCE524ABC1C812D15322A368AF51";


    @Mock
    private UserDetails userDetails;

    @Test
    void testGenerateTokenSuccessfully()
    {
        User user=User.builder().username("test").password("").build();
        String token=jwtService.generateToken(user);
        assertNotNull(token);
    }
    @Test
    void testExtractUsernameSuccessfully()
    {
        User user=User.builder().username("test").password("").build();

        String token=jwtService.generateToken(user);
        String extractedUsername=jwtService.extractUsername(token);
        assertEquals(extractedUsername,"test");
    }

    @Test
    void testExtractExpirationSuccessfully()
    {
        User user=User.builder().username("test").password("").build();

        String token=jwtService.generateToken(user);
        Date expiration=jwtService.extractExpiration(token);
        assertNotNull(expiration);
    }


    @Test
    void generateExpiredToken()
    {
        Map<String ,Object> extraClaims= new HashMap<>();
        String token=Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()-10000))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRETKEY)), SignatureAlgorithm.HS256)
                .compact();
        assertThrows(ExpiredJwtException.class, () -> {
            jwtService.isTokenValid(token, userDetails);
        });
    }

    @Test
    void generateTokenWithInvalidUsername()
    {
        Map<String ,Object> extraClaims= new HashMap<>();
        String token=Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject("test user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10000))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRETKEY)), SignatureAlgorithm.HS256)
                .compact();
        assertFalse(jwtService.isTokenValid(token, userDetails));
    }



}
