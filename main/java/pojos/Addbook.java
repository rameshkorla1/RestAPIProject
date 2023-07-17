package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Addbook {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("aisle")
	private String aisle;
	
	@JsonProperty("author")
	private String author;
	
	//Constructor 
//	public Addbook(String name, String isbn, String aisle, String author)
//	{
//		this.name = name;
//		this.isbn = isbn;
//		this.aisle = aisle;
//		this.author = author;
//	}
	//Getter and Setter methods

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAisle() {
		return aisle;
	}

	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

}
