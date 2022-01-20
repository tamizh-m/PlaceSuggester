package com.tamizh.PlaceSuggester.Interface;

import com.tamizh.PlaceSuggester.model.SuggestionResponse;

public interface SuggestionInterface {

	public SuggestionResponse getSuggestion(String location, String type, String latLng);
}
