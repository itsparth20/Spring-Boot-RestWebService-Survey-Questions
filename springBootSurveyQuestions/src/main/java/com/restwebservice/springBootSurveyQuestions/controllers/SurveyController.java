package com.restwebservice.springBootSurveyQuestions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
