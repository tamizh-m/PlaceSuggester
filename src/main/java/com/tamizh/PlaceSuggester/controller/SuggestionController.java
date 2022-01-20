package com.tamizh.PlaceSuggester.controller;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamizh.PlaceSuggester.model.SuggestionResponse;
import com.tamizh.PlaceSuggester.service.SuggestionService;

@RestController
@RequestMapping("suggestion")
public class SuggestionController {
	
	@Autowired
	private SuggestionService suggestionService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(produces = "application/json")
	public SuggestionResponse getSuggestion (@RequestParam(required = false) String location,
			@RequestParam("type") String type,
			@RequestParam(required = false)String latLng)
	{
		return suggestionService.getSuggestion(location,type, latLng);
	}
	
}
