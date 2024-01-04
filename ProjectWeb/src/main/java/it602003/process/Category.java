package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.CategoryObject;
//import it602003.objects.*;
//import it602003.process.*;

public class Category {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng category
	private ConnectionPool cp;

	public Category() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("category");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<CategoryObject> getCategoryObjects(CategoryObject similar, byte total) {
		ArrayList<CategoryObject> categorys = new ArrayList<>();
		CategoryObject category;

		String sql = "SELECT * FROM tblcategory ORDER BY category_id ASC LIMIT ?"; // Khởi tạo truy vấn SQL

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị

			// sau khi biên dịch thì truyền tham số vào
			pre.setByte(1, total); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về

			if (rs != null) {
				while (rs.next()) {
					category = new CategoryObject();
					category.setCategory_id(rs.getInt("category_id"));
					category.setCategory_name(rs.getString("category_name"));
					category.setCategory_total_book(rs.getInt("category_total_book"));
					category.setCreated_at(rs.getString("created_at"));
					category.setUpdated_at(rs.getString("updated_at"));
					// Add category vào list
					categorys.add(category);
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

		return categorys;
	}

	// Add Category
	public boolean addCategory(CategoryObject category) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblcategory (category_name, category_total_book)");
		sql.append(" VALUES (?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			// Set value cho các tham số
			pre.setString(1, category.getCategory_name());
			pre.setInt(2, 0);

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

	// Edit Category
	public boolean editCategory(CategoryObject category) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblcategory SET category_name = ?" + " WHERE category_id = ? ");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, category.getCategory_name());
			// pre.setInt(2, category.getCategory_total_book());
			pre.setInt(2, category.getCategory_id());

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

	// Remove Category
	public boolean removeCategory(int category_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblcategory WHERE category_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, category_id);

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

	// Search Category
	public CategoryObject searchCategory(int category_id) {
		String sql = "SELECT * FROM tblcategory WHERE category_id = ? LIMIT 1";

		CategoryObject category = new CategoryObject();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, category_id);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					category.setCategory_id(rs.getInt("category_id"));
					category.setCategory_name(rs.getString("category_name"));
					category.setCategory_total_book(rs.getInt("category_total_book"));
					category.setCreated_at(rs.getString("created_at"));
					category.setUpdated_at(rs.getString("updated_at"));
					return category;
				}
			}
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

		return null;
	}

	public ArrayList<CategoryObject> searchCategory(String category_name) {
		String sql = "SELECT * FROM tblcategory WHERE category_name LIKE CONCAT('%', ?, '%')";

		ArrayList<CategoryObject> categorys = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, category_name);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					CategoryObject category = new CategoryObject();
					category.setCategory_id(rs.getInt("category_id"));
					category.setCategory_name(rs.getString("category_name"));
					category.setCategory_total_book(rs.getInt("category_total_book"));
					category.setCreated_at(rs.getString("created_at"));
					category.setUpdated_at(rs.getString("updated_at"));
					categorys.add(category);
				}

			}
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
		return categorys;
	}

	public ArrayList<CategoryObject> getAllCategories() {
		ArrayList<CategoryObject> categories = new ArrayList<>();

		String sql = "SELECT * FROM tblcategory ORDER BY category_id ASC";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				CategoryObject category = new CategoryObject();
				category.setCategory_id(rs.getInt("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_total_book(rs.getInt("category_total_book"));
				category.setCreated_at(rs.getString("created_at"));
				category.setUpdated_at(rs.getString("updated_at"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// Đảm bảo đóng tất cả các tài nguyên
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categories;
	}

	// Tính toán sách trong category
	public boolean capNhat(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE tblcategory SET category_total_book = ( SELECT COUNT(book_inventory_number) FROM tblbook WHERE tblbook.category_id = tblcategory.category_id ) WHERE category_id = ? ;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, id);
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

	public int getBookTotal(int category_id) {
		int bookTotal = 0;

		String sql = "SELECT COUNT(*) FROM tblcategory INNER JOIN tblbook ON tblcategory.category_id = tblbook.category_id WHERE tblcategory.category_id = ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, category_id); // Set tham số author_id vào câu truy vấn

			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				bookTotal = rs.getInt(1); // Lấy giá trị từ cột COUNT(*)
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
				e1.printStackTrace();
			}
		}
		return bookTotal;
	}

//		public static void main(String[] args) {
//			Category category = new Category();
//			ArrayList<CategoryObject> arrayList1 = category.getCategoryObjects(null, (byte)5);
//			CategoryObject category1 = new CategoryObject();
//			category.searchCategory("anh").forEach(cate->{
//				System.out.println(cate.toString());
//			});;
//			
//		}

}
