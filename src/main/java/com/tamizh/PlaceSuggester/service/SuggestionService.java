package com.tamizh.PlaceSuggester.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datadog.api.v1.client.ApiClient;
import com.datadog.api.v1.client.ApiException;
import com.datadog.api.v1.client.Configuration;
import com.datadog.api.v1.client.api.EventsApi;
import com.datadog.api.v1.client.model.EventAlertType;
import com.datadog.api.v1.client.model.EventCreateRequest;
import com.datadog.api.v1.client.model.EventCreateResponse;
import com.tamizh.PlaceSuggester.Interface.SuggestionInterface;
import com.tamizh.PlaceSuggester.model.GeoCodeResponse;
import com.tamizh.PlaceSuggester.model.SuggestionResponse;

@Service
public class SuggestionService implements SuggestionInterface{

	@Value("${gmap_url}")
	private String gMapUrl;
	
	@Value("${gmap_apikey}")
	private String gMapApiKey;
	
	@Value("${gmap_geocode_url}")
	private String geoCodeUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	

	
	static Logger log = LogManager.getLogger(SuggestionService.class);
	
	@Override
	public SuggestionResponse getSuggestion(String location, String type, String latLng) {
		try {
			if(latLng == null) {
				latLng = getLatLng(location);
			}	
		String finalGmapUrl = gMapUrl+"location="+latLng+"&radius=1500&type="+type+"&key="+gMapApiKey;
		System.out.println(finalGmapUrl);
		URI uri = new URI(finalGmapUrl);
		ResponseEntity<SuggestionResponse> result = restTemplate.exchange(uri, HttpMethod.GET,null, SuggestionResponse.class);
		SuggestionResponse resultBody = result.getBody();
		System.out.println(resultBody);
		return resultBody;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	private String getLatLng(String location) {
		try {
		String latLng = null;
		String finalGeoCodeUrl = geoCodeUrl+location+"&key="+gMapApiKey;
		URI uri = new URI(finalGeoCodeUrl);
		ResponseEntity<GeoCodeResponse> result = restTemplate.exchange(uri, HttpMethod.GET,null, GeoCodeResponse.class);
		GeoCodeResponse resultBody = result.getBody();
		if(!resultBody.getResults().isEmpty()) {
		latLng = resultBody.getResults().get(0).getGeometry().getLocation().getLat()+","+resultBody.getResults().get(0).getGeometry().getLocation().getLng();
		}
		return latLng;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public void testMethod() {
		String test = "test";
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		HashMap<String, String> secrets = new HashMap<>();
		secrets.put("apiKeyAuth", "7eb652a4771311f8a4da52dbf4632830");
		secrets.put("appKeyAuth", "8c4673c217f09fcb73e37e833b16886b5b35eb5a");
		defaultClient.configureApiKeys(secrets);
	    EventsApi apiInstance = new EventsApi(defaultClient);

	    EventCreateRequest body =
	        new EventCreateRequest()
	            .title("Example-Post_an_event_returns_OK_response")
	            .text("A text message.")
	            .tags(
	                new ArrayList<String>() {
	                  {
	                    add("test:ExamplePostaneventreturnsOKresponse");
	                    add("exceptiontype"+test);
	                  }
	                }).sourceTypeName(test).aggregationKey("Cloud");

	    try {
	      EventCreateResponse result = apiInstance.createEvent(body);
	      System.out.println(result);
	    } catch (ApiException e) {
	      System.err.println("Exception when calling EventsApi#createEvent");
	      System.err.println("Status code: " + e.getCode());
	      System.err.println("Reason: " + e.getResponseBody());
	      System.err.println("Response headers: " + e.getResponseHeaders());
	      e.printStackTrace();
	    }
	  }
	}

	

