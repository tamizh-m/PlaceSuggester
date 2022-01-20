package com.tamizh.PlaceSuggester.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuggestionResponse{
    public List<Object> html_attributions;
    public String next_page_token;
    public List<Result> results;
    public String status;
}
