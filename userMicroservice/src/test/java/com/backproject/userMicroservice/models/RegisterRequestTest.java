package com.backproject.userMicroservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
public class RegisterRequestTest {

    @Test
    void RegisterRequestCreatedSuccessfully()
    {
        RegisterRequest registerRequest=new RegisterRequest();
        assertNotNull(registerRequest);
    }

    @Test
    void RegisterRequestBuilderWorkSuccessfully()
    {
        RegisterRequest registerRequest=RegisterRequest.builder()
                .username("testuser")
                .password("password")
                .build();
        assertAll(
                ()->assertEquals("testuser",registerRequest.getUsername()),
                ()->assertEquals("password",registerRequest.getPassword())
        );
    }



    @Test
    void RegisterRequestSettersAndGettersWorkSuccessfully()
    {
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("testpassword");
        assertAll(
                ()->assertEquals("testuser",registerRequest.getUsername()),
                ()->assertEquals("testpassword",registerRequest.getPassword())
        );
    }

    @Test
    void TwoRegisterRequestsAreEqual()
    {
        RegisterRequest request1=new RegisterRequest("testuser","1234");
        RegisterRequest request2=new RegisterRequest("testuser","1234");
        assertTrue(request1.equals(request2));
    }

    @Test
    void TwoRegisterRequestsAsStringsAreEqual()
    {
        RegisterRequest request1=new RegisterRequest("testuser","1234");
        RegisterRequest request2=new RegisterRequest("testuser","1234");
        String req1=request1.toString();
        String req2=request2.toString();
        assertTrue(req1.equals(req2));
    }

    @Test
    void TwoRegisterRequestsAsHashCodesAreEqual()
    {
        RegisterRequest request1=new RegisterRequest("testuser","1234");
        RegisterRequest request2=new RegisterRequest("testuser","1234");
        int req1=request1.hashCode();
        int req2=request2.hashCode();
        assertTrue(req1==req2);
    }

}
