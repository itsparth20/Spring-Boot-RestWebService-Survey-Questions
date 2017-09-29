package com.restwebservice.springBootSurveyQuestions.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservice.springBootSurveyQuestions.configuration.BasicConfiguration;

import javafx.util.Pair;
@RestController
public class WelcomeController {
	
	@Autowired
	BasicConfiguration basicConfig;
	
	@SuppressWarnings("restriction")
	@GetMapping("/welcome")
	public List<Pair<String,Object>> welcome(){
		List<Pair<String, Object>> list = new ArrayList<>();
		Pair<String, Object> pair1 = new Pair<>("Value", basicConfig.isValue());
		Pair<String, Object> pair2 = new Pair<>("Message", basicConfig.getMessage());
		Pair<String, Object> pair3 = new Pair<>("Number", basicConfig.getNumber());
		list.add(pair1);
		list.add(pair2);
		list.add(pair3);
		return list;
	}
}
