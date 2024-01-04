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

public class BorrowingForm {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng Book
	private ConnectionPool cp;

	public BorrowingForm() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("borrowing form");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BorrowingFormObject> getBorrowingForms(int page, int pageSize) {

		int start = (page - 1) * pageSize;
		ArrayList<BorrowingFormObject> bfs = new ArrayList<>();

		BorrowingFormObject bf;

		String sql = "SELECT * FROM tblborrowing_form ORDER BY user_id ASC LIMIT ? OFFSET ?";

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
					bf = new BorrowingFormObject();
					bf.setBorrowing_form_id(rs.getInt("borrowing_form_id"));
					bf.setBorrowing_form_date(rs.getString("borrowing_form_date"));
					bf.setBorrowing_form_type(rs.getString("borrowing_form_type"));
					bf.setBorrowing_form_deposit(rs.getDouble("borrowing_form_deposit"));
					bf.setBorrowing_form_due_date(rs.getString("borrowing_form_due_date"));
					bf.setUser_id(rs.getInt("user_id"));
					bf.setBook_id(rs.getInt("book_id"));
					bfs.add(bf);
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
		return bfs;
	}

	// Get by user_id
	public ArrayList<BorrowingFormObject> getBorrowingFormsByUserId(int user_id) {
		ArrayList<BorrowingFormObject> bfs = new ArrayList<>();

		BorrowingFormObject bf;

		String sql = "SELECT * FROM tblborrowing_form WHERE user_id = ? ORDER BY user_id ASC";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, user_id); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					bf = new BorrowingFormObject();
					bf.setBorrowing_form_id(rs.getInt("borrowing_form_id"));
					bf.setBorrowing_form_date(rs.getString("borrowing_form_date"));
					bf.setBorrowing_form_type(rs.getString("borrowing_form_type"));
					bf.setBorrowing_form_deposit(rs.getDouble("borrowing_form_deposit"));
					bf.setBorrowing_form_due_date(rs.getString("borrowing_form_due_date"));
					bf.setUser_id(rs.getInt("user_id"));
					bf.setBook_id(rs.getInt("book_id"));
					bfs.add(bf);
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
		return bfs;
	}

	// Add user has role is user
	public boolean addBorrowingForms(BorrowingFormObject br) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tblborrowing_form (borrowing_form_date, borrowing_form_type, borrowing_form_deposit, borrowing_form_due_date, user_id, book_id)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			LocalDateTime currentTime = LocalDateTime.now();
			String currentTimeString = currentTime.toString();

			// Tính toán thời gian sau 30 ngày
			LocalDateTime futureTime = currentTime.plusDays(30);
			String futureTimeString = futureTime.toString();

			// Set value cho các tham số
//				pre.setInt(1, user.getUser_id());
			pre.setString(1, br.getBorrowing_form_date());
			pre.setString(2, br.getBorrowing_form_type());
			pre.setDouble(3, br.getBorrowing_form_deposit());
			pre.setString(4, br.getBorrowing_form_due_date());
			pre.setInt(5, br.getUser_id());
			pre.setInt(6, br.getBook_id());

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

	// Remove User
	public boolean removeBorrowingFormsById(int br_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblborrowing_form WHERE borrowing_form_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, br_id);

			int result = pre.executeUpdate(); // return ra rowDeleted (row đã delete)
			if (result == 0) {
				this.con.rollback();
				return false;
			}

			this.con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	public int getTotalBorrowingForms() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM tblborrowing_form");
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

	public int countBookInBorrowingById(int book_id) {
		int total = 0;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM tblborrowing_form WHERE book_id = ? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, book_id);

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

	public BorrowingFormObject getBorrowingFormsById(int brId) {
		BorrowingFormObject bf = new BorrowingFormObject();;

		String sql = "SELECT * FROM tblborrowing_form WHERE borrowing_form_id = ? ORDER BY user_id ASC LIMIT 1";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, brId); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					bf.setBorrowing_form_id(rs.getInt("borrowing_form_id"));
					bf.setBorrowing_form_date(rs.getString("borrowing_form_date"));
					bf.setBorrowing_form_type(rs.getString("borrowing_form_type"));
					bf.setBorrowing_form_deposit(rs.getDouble("borrowing_form_deposit"));
					bf.setBorrowing_form_due_date(rs.getString("borrowing_form_due_date"));
					bf.setUser_id(rs.getInt("user_id"));
					bf.setBook_id(rs.getInt("book_id"));
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
		return bf;
	}
}
