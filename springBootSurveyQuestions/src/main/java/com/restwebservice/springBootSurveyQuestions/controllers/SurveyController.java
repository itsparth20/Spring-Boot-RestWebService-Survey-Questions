package com.restwebservice.springBootSurveyQuestions.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restwebservice.springBootSurveyQuestions.model.Question;
import com.restwebservice.springBootSurveyQuestions.services.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	SurveyService surveyService;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> getQuestionsForSurvey(@PathVariable String surveyId){
		return surveyService.retrieveQuestions(surveyId);
	}
	
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question getQuestionForSurvey(@PathVariable String surveyId, @PathVariable String questionId){
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionForSurvey(@PathVariable String surveyId, @RequestBody Question question){
		Question qus = surveyService.addQuestion(surveyId, question);
		
		if(qus == null)
			return ResponseEntity.noContent().build();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(qus.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
