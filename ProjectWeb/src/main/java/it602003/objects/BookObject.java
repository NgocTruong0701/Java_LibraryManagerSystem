package it602003.objects;

public class BookObject {
	private int book_id; 
	private String book_name; 
	private String book_publishing_year; 
	private String book_image;
	private String book_description; 
	private float book_price; 
	private int book_inventory_number; 
	private int book_page_number; 
	private String book_status; 
	private String book_language; 
	private int author_id; 
	private int category_id; 
	private int publisher_id; 
	private String created_at; 
	private String updated_at;
	
	
	
	public BookObject() {
		
	}



	public BookObject(int book_id, String book_name, String book_publishing_year, String book_image,
			String book_description, float book_price, int book_inventory_number, int book_page_number,
			String book_status, String book_language, int author_id, int category_id, int publisher_id,
			String created_at, String updated_at) {
		
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_publishing_year = book_publishing_year;
		this.book_image = book_image;
		this.book_description = book_description;
		this.book_price = book_price;
		this.book_inventory_number = book_inventory_number;
		this.book_page_number = book_page_number;
		this.book_status = book_status;
		this.book_language = book_language;
		this.author_id = author_id;
		this.category_id = category_id;
		this.publisher_id = publisher_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}



	public int getBook_id() {
		return book_id;
	}



	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}



	public String getBook_name() {
		return book_name;
	}



	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}



	public String getBook_publishing_year() {
		return book_publishing_year;
	}



	public void setBook_publishing_year(String book_publishing_year) {
		this.book_publishing_year = book_publishing_year;
	}



	public String getBook_image() {
		return book_image;
	}



	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}



	public String getBook_description() {
		return book_description;
	}



	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}



	public float getBook_price() {
		return book_price;
	}



	public void setBook_price(float book_price) {
		this.book_price = book_price;
	}



	public int getBook_inventory_number() {
		return book_inventory_number;
	}



	public void setBook_inventory_number(int book_inventory_number) {
		this.book_inventory_number = book_inventory_number;
	}



	public int getBook_page_number() {
		return book_page_number;
	}



	public void setBook_page_number(int book_page_number) {
		this.book_page_number = book_page_number;
	}



	public String getBook_status() {
		return book_status;
	}



	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}



	public String getBook_language() {
		return book_language;
	}



	public void setBook_language(String book_language) {
		this.book_language = book_language;
	}



	public int getAuthor_id() {
		return author_id;
	}



	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}



	public int getCategory_id() {
		return category_id;
	}



	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}



	public int getPublisher_id() {
		return publisher_id;
	}



	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
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
		return "BookObject [book_id=" + book_id + ", book_name=" + book_name + ", book_publishing_year="
				+ book_publishing_year + ", book_image=" + book_image + ", book_description=" + book_description
				+ ", book_price=" + book_price + ", book_inventory_number=" + book_inventory_number
				+ ", book_page_number=" + book_page_number + ", book_status=" + book_status + ", book_language="
				+ book_language + ", author_id=" + author_id + ", category_id=" + category_id + ", publisher_id="
				+ publisher_id + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
