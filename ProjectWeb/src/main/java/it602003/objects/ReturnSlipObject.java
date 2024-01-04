package it602003.objects;

public class ReturnSlipObject {
	private int return_slip_id; 
	private String return_slip_date; 
	private double return_slip_refund; 
	private double return_slip_late_fee; 
	private int user_id; 
	private int borrowing_form_id;
	private String created_at; 
	private String updated_at;
	public int getReturn_slip_id() {
		return return_slip_id;
	}
	
	public void setReturn_slip_id(int return_slip_id) {
		this.return_slip_id = return_slip_id;
	}
	public String getReturn_slip_date() {
		return return_slip_date;
	}
	public void setReturn_slip_date(String return_slip_date) {
		this.return_slip_date = return_slip_date;
	}
	public double getReturn_slip_refund() {
		return return_slip_refund;
	}
	public void setReturn_slip_refund(double return_slip_refund) {
		this.return_slip_refund = return_slip_refund;
	}
	public double getReturn_slip_late_fee() {
		return return_slip_late_fee;
	}
	public void setReturn_slip_late_fee(double return_slip_late_fee) {
		this.return_slip_late_fee = return_slip_late_fee;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBorrowing_form_id() {
		return borrowing_form_id;
	}
	public void setBorrowing_form_id(int borrowing_form_id) {
		this.borrowing_form_id = borrowing_form_id;
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
