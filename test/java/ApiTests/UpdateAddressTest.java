package ApiTests;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import ApiPayload.AddPlaceIdLoad;
import extentReports.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.AddPlace_Id;
import pojos.UpdateAddressRequest;
import pojos.locationPojo;
import reusable.ExcelReader;
@Listeners(extentReports.ExtentTestListener.class)
public class UpdateAddressTest {

	AddPlaceIdLoad addPlaceLoad;
    private Response response;
    AddPlace_Id addPlaceId;
  //  List<Map<String, Object>> list;
    private String placeId;
    
    RequestSpecification requestSpecification;
    private ExtentTest test;
  
    @Test(dataProvider = "testData")
    public void addPlace(Map<String, Object> testData)
    {
    	addPlaceId = new AddPlace_Id();
    	addPlaceId.setAccuracy((double) testData.get("accuracy"));
    	addPlaceId.setName((String) testData.get("name"));
        addPlaceId.setPhone_number((String) testData.get("phone_number"));
        addPlaceId.setAddress((String) testData.get("address"));
        //addPlaceId.setTypes((List<String>) testData.get("types"));
        addPlaceId.setWebsite((String) testData.get("website"));
        addPlaceId.setLanguage((String) testData.get("language"));

        locationPojo locations = new locationPojo();
        locations.setLat((double) testData.get("lat"));
        locations.setLng((double) testData.get("lng"));
        addPlaceId.setLocation(locations);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            String requestBody = mapper.writeValueAsString(addPlaceId);
            System.out.println(requestBody);
            ExtentTestManager.getTest().info("Request body: " + requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .addHeader("Content-Type", "application/json")
                .build();
        
        
        response = RestAssured.given()
                .log().all()
                .spec(requestSpecification)
                .body(addPlaceId)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .extract().response();
        placeId = response.jsonPath().getString("place_id");
        System.out.println(response.asString());
     // Log response to Extent Report        
        ExtentTestManager.getTest().info("Request body: \n" + response.asString());
        
        
    }
    
    @Test(dependsOnMethods = "addPlace")
    public void updateAddress() {
        // Create the update address request body
        UpdateAddressRequest updateRequest = new UpdateAddressRequest(placeId, "Tilak Road, Hyderabad", "qaclick123");

        // Convert the request body to JSON
        ObjectMapper mapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = mapper.writeValueAsString(updateRequest);
            System.out.println(requestBody);
            ExtentTestManager.getTest().info("Request body: \n" + requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Make the update address API call
        RestAssured.given()
                .log().all()
                .spec(requestSpecification)
                .body(updateRequest)
                .when()
                .post("/maps/api/place/update/json")
                .then()
                .log().all()
                .extract().response();
       ExtentTestManager.getTest().info("Response: " + response.asString());
    }

    @DataProvider(name = "testData")
    public Object[][] getTestData() {
        List<Map<String, Object>> testDataList = ExcelReader.readTestDataFromExcel("./ExcelFiles/AddPlace.xlsx", "Sheet1");
        Object[][] testDataArray = new Object[testDataList.size()][1];

        for (int i = 0; i < testDataList.size(); i++) {
            testDataArray[i][0] = testDataList.get(i);
        }

        return testDataArray;
    }
}
