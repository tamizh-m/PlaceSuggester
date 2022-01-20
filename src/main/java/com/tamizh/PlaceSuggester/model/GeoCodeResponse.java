package com.tamizh.PlaceSuggester.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoCodeResponse {
	
	    public List<GeoCodeResult> results;
	    public String status;
	
}
