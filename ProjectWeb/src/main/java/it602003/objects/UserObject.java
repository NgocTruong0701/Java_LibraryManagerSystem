package it602003.objects;

public class UserObject {
	private int user_id;
	private String user_name; 
	private String user_image; 
	private String user_phone_number; 
	private String user_address; 
	private String user_account_name; 
	private String user_account_password; 
	private int user_role; 
	private String created_at; 
	private String updated_at;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_account_name() {
		return user_account_name;
	}
	public void setUser_account_name(String user_account_name) {
		this.user_account_name = user_account_name;
	}
	public String getUser_account_password() {
		return user_account_password;
	}
	public void setUser_account_password(String user_account_password) {
		this.user_account_password = user_account_password;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
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

	public UserObject(int user_id, String user_name, String user_image, String user_phone_number, String user_address,
			String user_account_name, String user_account_password, int user_role, String created_at,
			String updated_at) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_image = user_image;
		this.user_phone_number = user_phone_number;
		this.user_address = user_address;
		this.user_account_name = user_account_name;
		this.user_account_password = user_account_password;
		this.user_role = user_role;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public UserObject(int user_id, String user_name, String user_image, String user_phone_number, String user_address,
			String user_account_name, String user_account_password, int user_role) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_image = user_image;
		this.user_phone_number = user_phone_number;
		this.user_address = user_address;
		this.user_account_name = user_account_name;
		this.user_account_password = user_account_password;
		this.user_role = user_role;
	}
	
	public UserObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
