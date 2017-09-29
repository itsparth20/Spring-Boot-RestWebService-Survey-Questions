package com.restwebservice.springBootSurveyQuestions.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restwebservice.springBootSurveyQuestions.SpringBootSurveyQuestionsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootSurveyQuestionsApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;
	

	@Test
	public void test() {
		System.out.println("Port : " + port);
	}

}
