package com.example.flowabledemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowableDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void dummyTestIntegers(){
		Assertions.assertEquals(5,5,"Dummy assertion integers");
	}

	@Test
	void dummyTestStrings(){
		Assertions.assertNotEquals("hallo","velo","Dummy assertion test strings");
	}

	@Test
	void dummyTestBooleans(){
		Assertions.assertTrue(true,"Dummy assertion test booleans");
	}

}
