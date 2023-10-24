package com.backproject.userMicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserMicroserviceApplicationTests {

	@Test
	void contextLoads() {

	}

}
