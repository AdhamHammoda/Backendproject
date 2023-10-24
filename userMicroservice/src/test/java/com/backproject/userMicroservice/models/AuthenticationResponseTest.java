package com.backproject.userMicroservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
public class AuthenticationResponseTest {
    @Test
    void AuthenticationResponseCreatedSuccessfully()
    {
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        assertNotNull(authenticationResponse);
    }

    @Test
    void AuthenticationResponseBuilderWorkSuccessfully()
    {
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .token("token")
                .build();
                assertEquals("token",authenticationResponse.getToken());
    }


    @Test
    void AuthenticationResponseSettersAndGettersWorkSuccessfully()
    {
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken("token");
        assertEquals("token",authenticationResponse.getToken());
    }

    @Test
    void TwoAuthenticationResponsesAreEqual()
    {
        AuthenticationResponse authenticationResponse1=new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse2=new AuthenticationResponse("token");
        assertTrue(authenticationResponse1.equals(authenticationResponse2));
    }

    @Test
    void TwoAuthenticationResponsesAsStringsAreEqual()
    {
        AuthenticationResponse response1=new AuthenticationResponse("token");
        AuthenticationResponse response2=new AuthenticationResponse("token");
        String res1=response1.toString();
        String res2=response2.toString();
        assertTrue(res1.equals(res2));
    }

    @Test
    void TwoAuthenticationResponsesAsHashCodesAreEqual()
    {
        AuthenticationResponse response1=new AuthenticationResponse("token");
        AuthenticationResponse response2=new AuthenticationResponse("token");
        int res1=response1.hashCode();
        int res2=response2.hashCode();
        assertTrue(res1==res2);
    }

}
