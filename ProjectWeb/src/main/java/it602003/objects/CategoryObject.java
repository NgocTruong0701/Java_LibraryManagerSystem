package it602003.objects;

public class CategoryObject {
	private int category_id; 
	private String category_name; 
	private int category_total_book; 
	private String created_at; 
	private String updated_at;
	
	public CategoryObject() {
		
	}
	
	public CategoryObject(int category_id, String category_name, int category_total_book, String created_at,
			String updated_at) {
		
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_total_book = category_total_book;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getCategory_total_book() {
		return category_total_book;
	}

	public void setCategory_total_book(int category_total_book) {
		this.category_total_book = category_total_book;
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
		return "CategoryObject [category_id=" + category_id + ", category_name=" + category_name
				+ ", category_total_book=" + category_total_book + ", created_at=" + created_at + ", updated_at="
				+ updated_at + "]";
	}
	
	
	
}
