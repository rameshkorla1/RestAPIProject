package ApiTests;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.AddPlaceId;
import pojos.Location;

public class PostRequestBodyExample {
	public static void main(String[] args) {
        // Create an instance of your POJO class representing the request body
        AddPlaceId addPlaceId = new AddPlaceId();
        addPlaceId.setAccuracy(50);
        addPlaceId.setName("Forntline house");
        addPlaceId.setPhoneNumber("(+91) 994 811 6665");
        addPlaceId.setAddress("29, side layout, cohen 09");
        addPlaceId.setTypes(new ArrayList<>()); // Empty list
        addPlaceId.setWebsite("http://rahulshettyacademy.com");
        addPlaceId.setLanguage("Telugu-IN");
        
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlaceId.setLocation(location);

        // Convert the object to JSON string
        ObjectMapper mapper = new ObjectMapper();
        try {
            String requestBody = mapper.writeValueAsString(addPlaceId);
            System.out.println(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
