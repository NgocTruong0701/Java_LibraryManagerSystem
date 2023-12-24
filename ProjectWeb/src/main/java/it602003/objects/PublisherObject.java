package it602003.objects;

public class PublisherObject {
	private int publisher_id;
	private String publisher_name; 
	private String publisher_phone_number; 
	private String publisher_address; 
	private String created_at; 
	private String updated_at;
	
	
	public PublisherObject(int publisher_id, String publisher_name, String publisher_phone_number,
			String publisher_address, String created_at, String updated_at) {
		super();
		this.publisher_id = publisher_id;
		this.publisher_name = publisher_name;
		this.publisher_phone_number = publisher_phone_number;
		this.publisher_address = publisher_address;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public PublisherObject() {
		super();
	}

	public String getPublisher_name() {
		return publisher_name;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getPublisher_phone_number() {
		return publisher_phone_number;
	}
	public void setPublisher_phone_number(String publisher_phone_number) {
		this.publisher_phone_number = publisher_phone_number;
	}
	public String getPublisher_address() {
		return publisher_address;
	}
	public void setPublisher_address(String publisher_address) {
		this.publisher_address = publisher_address;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "PublisherObject [publisher_id=" + publisher_id + ", publisher_name=" + publisher_name
				+ ", publisher_phone_number=" + publisher_phone_number + ", publisher_address=" + publisher_address
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	

}
//Publisherobject