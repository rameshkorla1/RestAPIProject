package ApiPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import pojos.AddPlaceId;
import pojos.Location;
import pojos.Types;

public class AddPlaceIdLoad {
	
	AddPlaceId placeId = new AddPlaceId();
	Location location;
	Types types;
	List<String> type = new ArrayList<String>();
	List<Location> listOfLocation = new ArrayList<Location>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public void setAppPlaceIdDetails(Integer accuracy, String name, String phoneNumber, String address, String website, String language)
	{
		setLocation(-38.383494, 33.427362);
	//    placeId.setLocation(getLocationList());
//		placeId.setTypes(getTypes());
		setAddPlace(50, "Forntline house", "(+91) 994 811 6665", "29, side layout, cohen 09", "http://rahulshettyacademy.com", "Telugu-IN");
	}
	


	public void setAddPlace(Integer accuracy, String name, String phoneNumber, String address, String website, String language)
	{		
		placeId.setAccuracy(accuracy);
		placeId.setName(name);
		placeId.setPhoneNumber(phoneNumber);
		placeId.setAddress(address);
		placeId.setTypes(getTypes());
		placeId.setWebsite(website);
		placeId.setLanguage(language);
	}

	public void setLocation(double lat, double lng)
	{
		location = new Location();
		location.setLat(lat);
		location.setLng(lng);
	}
	
	public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
	
	 public void setAdditionalProperty(String name, Object value) {
	        this.additionalProperties.put(name, value);
	    }
	
	public void setLocationList(){
		listOfLocation.add(location);
	}

	public List<Location> getLocationList(){
		return listOfLocation;
	}
	
	public List<String> getTypes() {
        return type;
    }
	
	public void printL(){
		System.out.println(type);
	}
	
	public void setTypes(List<String> types) {
        this.type = types;
    }
	
	public AddPlaceId getAddPlaceIdPayload(){
		return placeId;
	}


	public void setTypes(String s1, String s2)
	{
		types = new Types();
		types.setS1(s1);
		types.setS2(s2);
	}

//	public void setLocation(double lat, double lng) {
//		location = new Location();
//		location.setLat(lat);
//		location.setLng(lng);
//		
//	}

}
