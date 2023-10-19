package com.backproject.userMicroservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserTest {
    @Test
    void UserCreatedSuccessfully()
    {
        User user=new User("","");
        assertNotNull(user);
    }

    @Test
    void UserMethodsWorkSuccessfully()
    {
        User user=User.builder()
                .username("testuser")
                .password("password")
                .role(Role.USER)
                .build();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertAll(
                ()->assertEquals("testuser",user.getUsername()),
                ()->assertEquals("password",user.getPassword()),
                ()->assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")))
                );
    }


}
