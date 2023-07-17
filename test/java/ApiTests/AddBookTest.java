package ApiTests;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import extentReports.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.Addbook;

@Listeners(extentReports.ExtentTestListener.class)
public class AddBookTest {

	Addbook addBook;
	RequestSpecification requestSpecification;
	Response response;
	
	@Test
	public void addBook()
	{
		addBook = new Addbook();
		addBook.setName("Learn Appium Automation with Java");
		addBook.setIsbn("ytnft");
		addBook.setAisle("227");
		addBook.setAuthor("John foe");
		
		ObjectMapper mapper = new ObjectMapper();
        try {
            String requestBody = mapper.writeValueAsString(addBook);
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
                .body(addBook)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .log().all()
                .extract().response();
        //System.out.println(response.asString());
        ExtentTestManager.getTest().info("Response body: \n" + response.asString());

	}
}
