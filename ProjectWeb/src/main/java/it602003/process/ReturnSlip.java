package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.BorrowingFormObject;
import it602003.objects.ReturnSlipObject;

public class ReturnSlip {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng Book
	private ConnectionPool cp;

	public ReturnSlip() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("return slip");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ReturnSlipObject> getReturnSlips(int page, int pageSize) {

		int start = (page - 1) * pageSize;
		ArrayList<ReturnSlipObject> rls = new ArrayList<>();

		ReturnSlipObject rl;

		String sql = "SELECT * FROM tblreturn_slip ORDER BY user_id ASC LIMIT ? OFFSET ?";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, pageSize); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn
			pre.setInt(2, start);

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					rl = new ReturnSlipObject();
					rl.setReturn_slip_id(rs.getInt("return_slip_id"));
					rl.setReturn_slip_date(rs.getString("return_slip_date"));
					rl.setReturn_slip_refund(rs.getDouble("return_slip_refund"));
					rl.setReturn_slip_late_fee(rs.getDouble("return_slip_late_fee"));
					rl.setUser_id(rs.getInt("user_id"));
					rl.setBorrowing_form_id(rs.getInt("borrowing_form_id"));
					rls.add(rl);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return rls;
	}

	// Get by user_id
	public ArrayList<ReturnSlipObject> getReturnSlipsByUserId(int user_id) {
		ArrayList<ReturnSlipObject> rls = new ArrayList<>();

		ReturnSlipObject rl;

		String sql = "SELECT * FROM tblreturn_slip WHERE user_id = ? ORDER BY user_id ASC";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, user_id); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					rl = new ReturnSlipObject();
					rl.setReturn_slip_id(rs.getInt("return_slip_id"));
					rl.setReturn_slip_date(rs.getString("return_slip_date"));
					rl.setReturn_slip_refund(rs.getDouble("return_slip_refund"));
					rl.setReturn_slip_late_fee(rs.getDouble("return_slip_late_fee"));
					rl.setUser_id(rs.getInt("user_id"));
					rl.setBorrowing_form_id(rs.getInt("borrowing_form_id"));
					rls.add(rl);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return rls;
	}

	public boolean addBorrowingForms(ReturnSlipObject rl) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tblreturn_slip (return_slip_date, return_slip_refund, return_slip_late_fee, user_id, borrowing_form_id)");
		sql.append("VALUES (?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			LocalDateTime currentTime = LocalDateTime.now();
			String currentTimeString = currentTime.toString();

			// Set value cho các tham số
//				pre.setInt(1, user.getUser_id());
			pre.setString(1, rl.getReturn_slip_date());
			pre.setDouble(2, rl.getReturn_slip_refund());
			pre.setDouble(3, rl.getReturn_slip_late_fee());
			pre.setInt(4, rl.getUser_id());
			pre.setInt(5, rl.getBorrowing_form_id());

			// thực thi - ở vùng rollback, vùng kết nối mềm để xem câu lệnh sql này có vấn
			// đề này không, nếu ổn thì mới commit
			int result = pre.executeUpdate(); // return ra rowInserted (row đã insert vào)
			if (result == 0) {
				// Có lỗi thực hiện rollback
				this.con.rollback();
				return false;
			}

			// thực hiện commit - thực thi câu lệnh trên db
			this.con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}


	public int getTotalReturnSlip() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM tblreturn_slip");
		int total = 0;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					total = rs.getInt(1);
				}
			}

			// Đóng ResultSet và PreparedStatement
			if (rs != null) {
				rs.close();
			}
			if (pre != null) {
				pre.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return total;
	}
}
