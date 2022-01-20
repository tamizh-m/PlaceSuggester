package com.tamizh.PlaceSuggester.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoCodeResult {

	    public String formatted_address;
	    public Geometry geometry;
	    public String place_id;
	    public List<String> types;
	
}
