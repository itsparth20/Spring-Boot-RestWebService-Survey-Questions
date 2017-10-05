package com.restwebservice.springBootSurveyQuestions.controllers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.restwebservice.springBootSurveyQuestions.SpringBootSurveyQuestionsApplication;
import com.restwebservice.springBootSurveyQuestions.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootSurveyQuestionsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	TestRestTemplate template;
	
	

	@Test
	public void testRetrieveSurveyQuestion() {

		String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	//NEEDS REFACTORING
		@Test
		public void addQuestion() {

			String url = "http://localhost:" + port + "/surveys/Survey1/questions";

			TestRestTemplate restTemplate = new TestRestTemplate();

			HttpHeaders headers = new HttpHeaders();

			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			Question question = new Question("DOESNTMATTER", "Question1", "Russia",
					Arrays.asList("India", "Russia", "United States", "China"));

			HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

			ResponseEntity<String> response = restTemplate.exchange(url,
					HttpMethod.POST, entity, String.class);

			String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

			assertTrue(actual.contains("/surveys/Survey1/questions/"));

		}

}
