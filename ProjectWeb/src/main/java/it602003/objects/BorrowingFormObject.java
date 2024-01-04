package it602003.objects;

public class BorrowingFormObject {
	private int borrowing_form_id;
	private String borrowing_form_date;
	private String borrowing_form_type;
	private double borrowing_form_deposit;
	private String borrowing_form_due_date;
	private int user_id;
	private int book_id;
	private String created_at;
	private String updated_at;
	public int getBorrowing_form_id() {
		return borrowing_form_id;
	}
	public void setBorrowing_form_id(int borrowing_form_id) {
		this.borrowing_form_id = borrowing_form_id;
	}
	public String getBorrowing_form_date() {
		return borrowing_form_date;
	}
	public void setBorrowing_form_date(String borrowing_form_date) {
		this.borrowing_form_date = borrowing_form_date;
	}
	public String getBorrowing_form_type() {
		return borrowing_form_type;
	}
	public void setBorrowing_form_type(String borrowing_form_type) {
		this.borrowing_form_type = borrowing_form_type;
	}
	public double getBorrowing_form_deposit() {
		return borrowing_form_deposit;
	}
	public void setBorrowing_form_deposit(double borrowing_form_deposit) {
		this.borrowing_form_deposit = borrowing_form_deposit;
	}
	public String getBorrowing_form_due_date() {
		return borrowing_form_due_date;
	}
	public void setBorrowing_form_due_date(String borrowing_form_due_date) {
		this.borrowing_form_due_date = borrowing_form_due_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
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
