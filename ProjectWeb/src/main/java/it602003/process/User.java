package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.UserObject;

public class User {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng user
	private ConnectionPool cp;

	public User() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("User");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get all user has role is user
	public ArrayList<UserObject> getuserObjects(int role, byte total) {
		ArrayList<UserObject> users = new ArrayList<>();

		UserObject user;

		String sql = "SELECT * FROM tbluser WHERE user_role = ? ORDER BY user_id ASC LIMIT ?";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, role);
			pre.setByte(2, total); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					user = new UserObject();
					user.setUser_id(rs.getInt("user_id"));
					user.setUser_name(rs.getString("user_name"));
					user.setUser_image(rs.getString("user_image"));
					user.setUser_phone_number(rs.getString("user_phone_number"));
					user.setUser_address(rs.getString("user_address"));
					user.setUser_account_name(rs.getString("user_account_name"));
					user.setUser_account_password(rs.getString("user_account_password"));
					users.add(user);
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
		return users;
	}

	// Add user has role is user
	public boolean addUser(UserObject user) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tbluser (user_name, user_image, user_phone_number, user_address, user_account_name, user_account_password, user_role)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			// Set value cho các tham số
//			pre.setInt(1, user.getUser_id());
			pre.setString(1, user.getUser_name());
			pre.setString(2, user.getUser_image());
			pre.setString(3, user.getUser_phone_number());
			pre.setString(4, user.getUser_address());
			pre.setString(5, user.getUser_account_name());
			pre.setString(6, user.getUser_account_password());
			pre.setInt(7, user.getUser_role());
//			pre.setString(9, user.getCreated_at());
//			pre.setString(10, user.getUpdated_at());

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

	// Edit User
	public boolean editUser(UserObject user) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser SET ");
		sql.append(
				"user_name = ?, user_image = ?, user_phone_number = ?, user_address = ?, user_account_name = ?, user_account_password = ?, user_role = ?, updated_at = ? ");
		sql.append("WHERE user_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, user.getUser_name());
			pre.setString(2, user.getUser_image());
			pre.setString(3, user.getUser_phone_number());
			pre.setString(4, user.getUser_address());
			pre.setString(5, user.getUser_account_name());
			pre.setString(6, user.getUser_account_password());
			pre.setInt(7, user.getUser_role());

			int result = pre.executeUpdate(); // return ra rowUpdated (row đã được update)
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
	
	// Remove User
	public boolean removeUser(int user_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tbluser WHERE product_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, user_id);

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

}
