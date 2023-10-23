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
    void UserBuilderWorkSuccessfully()
    {
        User user=User.builder()
                .username("testuser")
                .password("password")
                .role(Role.USER)
                .id(2)
                .build();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertAll(
                ()->assertEquals("testuser",user.getUsername()),
                ()->assertEquals("password",user.getPassword()),
                ()->assertTrue(authorities.contains(new SimpleGrantedAuthority("USER"))),
                ()->assertEquals(2,user.getId()),
                ()->assertEquals(Role.USER,user.getRole())
                );
    }

    @Test
    void UserSettersAndGettersWorkSuccessfully()
    {
        User user=new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setId(2);
        user.setRole(Role.USER);
        assertAll(
                ()->assertEquals("testuser",user.getUsername()),
                ()->assertEquals("testpassword",user.getPassword()),
                ()->assertEquals(2,user.getId()),
                ()->assertEquals(Role.USER,user.getRole())

        );
    }

    @Test
    void UserAccountNonExpiredIsTrue()
    {
        User user=new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void UserCredentialsNonExpiredIsTrue()
    {
        User user=new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void UserEnabledIsTrue()
    {
        User user=new User();
        assertTrue(user.isEnabled());
    }

    @Test
    void UserAccountNonLockedIsTrue()
    {
        User user=new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void TwoUsersAreEqual()
    {
        User user1=new User("testuser","1234");
        User user2=new User("testuser","1234");
        assertTrue(user1.equals(user2));
    }

    @Test
    void TwoUsersAsStringsAreEqual()
    {
        User user1=new User("testuser","1234");
        User user2=new User("testuser","1234");
        String user1AsString=user1.toString();
        String user2AsString=user2.toString();
        assertTrue(user1AsString.equals(user2AsString));
    }

    @Test
    void TwoUsersAsHashCodesAreEqual()
    {
        User user1=new User("testuser","1234");
        User user2=new User("testuser","1234");
        int hash1=user1.hashCode();
        int hash2=user2.hashCode();
        assertTrue(hash1==hash2);
    }







}
