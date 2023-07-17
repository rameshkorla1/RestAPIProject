package ApiTests;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ApiPayload.AddPlaceIdLoad;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojos.AddPlace_Id;
import pojos.locationPojo;

public class AddPlaceIdTest {
//	
//	AddPlaceIdLoad  addPlaceLoad = new AddPlaceIdLoad();
//	Response response;
//	List<Map<String, Object>> list;
//	
//	RequestSpecification requestSpecification;
//	ResponseSpecification responseSpecification;
//	PrintStream printStream;
//	
//	@Test
//	public void addPlace() throws JsonProcessingException
//	{
//		addPlaceLoad = new AddPlaceIdLoad();
//		//addPlaceLoad.setAppPlaceIdDetails(50, "Forntline house", "(+91) 994 811 6665", "29, side layout, cohen 09", "http://rahulshettyacademy.com", "Telugu-IN");
//		addPlaceLoad.setLocation(-38.383494, 33.427362);
//		addPlaceLoad.setTypes("shoe type", "shoe");
//		addPlaceLoad.setAddPlace(50, "Forntline house", "(+91) 994 811 6665", "29, side layout, cohen 09", "http://rahulshettyacademy.com", "Telugu-IN");
//		ObjectMapper mapper = new ObjectMapper();
//		String s = mapper.writeValueAsString(addPlaceLoad.getAddPlaceIdPayload());
//		response = RestAssured.given().log().all().queryParam("key", "qaclick123").spec(createRequestSpec()).body(s).when().post("https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123")
//				.then().extract().response();
//		System.out.println(response.asString());
//	}
//
//	
//	public RequestSpecification createRequestSpec(){
//		 requestSpecification = new RequestSpecBuilder()
//				.addHeader("Content-Type","application/json")
////				.addFilter(RequestLoggingFilter.logRequestTo(printStream))
////				.addFilter(ResponseLoggingFilter.logResponseTo(printStream))
//				.build();
//		 return requestSpecification;
//	}
	AddPlaceIdLoad addPlaceLoad;
    Response response;
    AddPlace_Id addPlaceId;
  //  List<Map<String, Object>> list;

    RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
//        addPlaceLoad = new AddPlaceIdLoad();
//        addPlaceLoad.setLocation(-38.383494, 33.427362);
//        addPlaceLoad.setTypes("shoe type", "shoe");
//        addPlaceLoad.setAddPlace(50, "Forntline house", "(+91) 994 811 6665", "29, side layout, cohen 09", "http://rahulshettyacademy.com", "Telugu-IN");
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
            String requestBody = mapper.writeValueAsString(addPlaceId);
            System.out.println(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @Test
    public void addPlace() {
        response = RestAssured.given()
                .log().all()
                .spec(requestSpecification)
                .body(addPlaceId)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .extract().response();

        System.out.println(response.asString());
    }
}
