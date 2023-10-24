package com.backproject.userMicroservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
public class AuthenticationRequestTest {
    @Test
    void AuthenticationRequestCreatedSuccessfully()
    {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        assertNotNull(authenticationRequest);
    }

    @Test
    void AuthenticationRequestBuilderWorkSuccessfully()
    {
        AuthenticationRequest authenticationRequest=AuthenticationRequest.builder()
                .username("testuser")
                .password("password")
                .build();
        assertAll(
                ()->assertEquals("testuser",authenticationRequest.getUsername()),
                ()->assertEquals("password",authenticationRequest.getPassword())
        );
    }



    @Test
    void AuthenticationRequestSettersAndGettersWorkSuccessfully()
    {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        authenticationRequest.setUsername("testuser");
        authenticationRequest.setPassword("testpassword");
        assertAll(
                ()->assertEquals("testuser",authenticationRequest.getUsername()),
                ()->assertEquals("testpassword",authenticationRequest.getPassword())
        );
    }

    @Test
    void TwoAuthenticationRequestsAreEqual()
    {
        AuthenticationRequest request1=new AuthenticationRequest("testuser","1234");
        AuthenticationRequest request2=new AuthenticationRequest("testuser","1234");
        assertTrue(request1.equals(request2));
    }

    @Test
    void TwoAuthenticationRequestsAsStringsAreEqual()
    {
        AuthenticationRequest request1=new AuthenticationRequest("testuser","1234");
        AuthenticationRequest request2=new AuthenticationRequest("testuser","1234");
        String req1=request1.toString();
        String req2=request2.toString();
        assertTrue(req1.equals(req2));
    }

    @Test
    void TwoAuthenticationRequestsAsHashCodesAreEqual()
    {
        AuthenticationRequest request1=new AuthenticationRequest("testuser","1234");
        AuthenticationRequest request2=new AuthenticationRequest("testuser","1234");
        int req1=request1.hashCode();
        int req2=request2.hashCode();
        assertTrue(req1==req2);
    }

}