package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAddressRequest {

	    @JsonProperty("place_id")
	    private String placeId;

	    @JsonProperty("address")
	    private String address;

	    @JsonProperty("key")
	    private String key;

	    // Constructors, getters, setters, and other methods (if needed)
	    
	    // Constructor
	    public UpdateAddressRequest(String placeId, String address, String key) {
	        this.placeId = placeId;
	        this.address = address;
	        this.key = key;
	    }
	    // Getters and Setters (You can use Lombok library to auto-generate getters/setters)
		public String getPlaceId() {
			return placeId;
		}

		public void setPlaceId(String placeId) {
			this.placeId = placeId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
}
