package com.tamizh.PlaceSuggester.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result{
 
    public String name;
    public List<String> types;
    public String vicinity;
    public String business_status;
    public OpeningHours opening_hours;
    public double rating;
    public int user_ratings_total;
}
