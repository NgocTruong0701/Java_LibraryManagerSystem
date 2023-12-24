package it602003.objects;

public class AuthorObject {
	private int author_id;
	private String author_name; 
	private String author_date_of_birth;
	private String author_description;
	private String author_image;
	private String created_at;
	private String updated_at;
	
	public AuthorObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthorObject(int author_id, String author_name, String author_date_of_birth, String author_description,
			String author_image, String created_at, String updated_at) {
		super();
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_date_of_birth = author_date_of_birth;
		this.author_description = author_description;
		this.author_image = author_image;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_date_of_birth() {
		return author_date_of_birth;
	}
	public void setAuthor_date_of_birth(String author_date_of_birth) {
		this.author_date_of_birth = author_date_of_birth;
	}
	public String getAuthor_description() {
		return author_description;
	}
	public void setAuthor_description(String author_description) {
		this.author_description = author_description;
	}
	public String getAuthor_image() {
		return author_image;
	}
	public void setAuthor_image(String author_image) {
		this.author_image = author_image;
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
	
}
