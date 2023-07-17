package ApiTests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.AddPlace_Id;
import pojos.locationPojo;

public class AddPlaceIdTest2 {
	
	Response response;
    AddPlace_Id addPlaceId;
  //  List<Map<String, Object>> list;
    String requestBody;
    RequestSpecification requestSpecification;
	
	@Test
	public void addPlace() throws JsonProcessingException
	{
		addPlaceId = new AddPlace_Id();
    	addPlaceId.setAccuracy(50);
        addPlaceId.setName("Forntline house");
        addPlaceId.setPhone_number("(+91) 994 811 6665");
        addPlaceId.setAddress("29, side layout, cohen 09");
        addPlaceId.setTypes(new ArrayList<>()); // Empty list
        addPlaceId.setWebsite("http://rahulshettyacademy.com");
        addPlaceId.setLanguage("Telugu-IN");
        
        locationPojo locations = new locationPojo();
        locations.setLat(-38.383494);
        locations.setLng(33.427362);
        addPlaceId.setLocation(locations);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
             requestBody = mapper.writeValueAsString(addPlaceId);
            System.out.println(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        response = RestAssured.given().log().all().queryParam("key", "qaclick123")
        		.spec(createRequestSpec()).body(requestBody).when()
        		.post("https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123")
				.then().log().all().extract().response();
		System.out.println(response.asString());
	}
	public RequestSpecification createRequestSpec(){
	 requestSpecification = new RequestSpecBuilder()
			.addHeader("Content-Type","application/json")
//			.addFilter(RequestLoggingFilter.logRequestTo(printStream))
//			.addFilter(ResponseLoggingFilter.logResponseTo(printStream))
			.build();
	 return requestSpecification;
}
}
