package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.AuthorObject;

public class Author {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng author
	private ConnectionPool cp;

	public Author() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("Author");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get all author has role is author
	public ArrayList<AuthorObject> getAuthorObjects(int page, int pageSize) {
		
		int start = (page - 1) * pageSize;
		ArrayList<AuthorObject> authors = new ArrayList<>();

		AuthorObject author;

		String sql = "SELECT * FROM tblauthor ORDER BY author_id ASC LIMIT ? OFFSET ?";

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
					author = new AuthorObject();
					author.setAuthor_id(rs.getInt("author_id"));
					author.setAuthor_name(rs.getString("author_name"));
					
					DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        // Chuyển đổi ngày tháng từ định dạng cũ sang định dạng mới
			        LocalDate date = LocalDate.parse(rs.getString("author_date_of_birth"), originalFormatter);
			        String formattedDate = date.format(newFormatter);
					author.setAuthor_date_of_birth(formattedDate);
					
					author.setAuthor_description(rs.getString("author_description"));
					author.setAuthor_image(rs.getString("author_image"));
					author.setCreated_at(rs.getString("created_at"));
					author.setUpdated_at(rs.getString("updated_at"));
					authors.add(author);
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
		return authors;
	}
	
	// Get author by author ID
	public AuthorObject getAuthorObjectsById(int author_id) {
		AuthorObject author = new AuthorObject();

		String sql = "SELECT * FROM tblauthor WHERE author_id = ? LIMIT 1";

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị
			// sau khi biên dịch thì truyền tham số vào
			pre.setInt(1, author_id);// Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về
			if (rs != null) {
				while (rs.next()) {
					author = new AuthorObject();
					author.setAuthor_id(rs.getInt("author_id"));
					author.setAuthor_name(rs.getString("author_name"));
					author.setAuthor_date_of_birth(rs.getString("author_date_of_birth"));
					author.setAuthor_description(rs.getString("author_description"));
					author.setAuthor_image(rs.getString("author_image"));
					author.setCreated_at(rs.getString("created_at"));
					author.setUpdated_at(rs.getString("updated_at"));
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
		return author;
	}

	// Add author
	public boolean addAuthor(AuthorObject author) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tblauthor (author_name, author_date_of_birth, author_description, author_image)");
		sql.append("VALUES (?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			// Set value cho các tham số
			pre.setString(1, author.getAuthor_name());
			pre.setString(2, author.getAuthor_date_of_birth());
			pre.setString(3, author.getAuthor_description());
			pre.setString(4, author.getAuthor_image());

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

	// Edit author
	public boolean editAuthor(int author_id, AuthorObject author) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblauthor SET ");
		sql.append(
				"author_name = ?, author_date_of_birth = ?, author_description = ?, author_image = ?");
		sql.append("WHERE author_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, author.getAuthor_name());
			pre.setString(2, author.getAuthor_date_of_birth());
			pre.setString(3, author.getAuthor_description());
			pre.setString(4, author.getAuthor_image());
			pre.setInt(5, author_id);

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
	
	// Remove author
	public boolean removeauthor(int author_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblauthor WHERE author_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, author_id);

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
	
	
	public int getTotalAuthors() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM tblauthor");
		int total = 0;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			
			ResultSet rs = pre.executeQuery();
			if(rs != null) {
				while(rs.next()) {
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
		}
		catch(Exception e) {
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
	
	public ArrayList<AuthorObject> getAllAuthors() {
	    ArrayList<AuthorObject> authors = new ArrayList<>();

	    String sql = "SELECT * FROM tblauthor ORDER BY author_id ASC";

	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        ResultSet rs = pre.executeQuery();

	        while (rs.next()) {
	            AuthorObject author = new AuthorObject();
	            author.setAuthor_id(rs.getInt("author_id"));
	            author.setAuthor_name(rs.getString("author_name"));
	            author.setAuthor_date_of_birth(rs.getString("author_date_of_birth"));
	            author.setAuthor_description(rs.getString("author_description"));
	            author.setAuthor_image(rs.getString("author_image"));
	            author.setCreated_at(rs.getString("created_at"));
	            author.setUpdated_at(rs.getString("updated_at"));
	            authors.add(author);
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

	    return authors;
	}
	
	public int getBookTotal(int author_id) {
	    int bookTotal = 0;
	    
	    String sql = "SELECT COUNT(*) FROM tblauthor INNER JOIN tblbook ON tblauthor.author_id = tblbook.author_id WHERE tblauthor.author_id = ?";
	    
	    try {
	        PreparedStatement pre = this.con.prepareStatement(sql);
	        pre.setInt(1, author_id); // Set tham số author_id vào câu truy vấn
	        
	        ResultSet rs = pre.executeQuery();
	        if(rs.next()) {
	            bookTotal = rs.getInt(1); // Lấy giá trị từ cột COUNT(*)
	        }
	        
	        // Đóng ResultSet và PreparedStatement
	        if (rs != null) {
	            rs.close();
	        }
	        if (pre != null) {
	            pre.close();
	        }
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        try {
	            con.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return bookTotal;
	}

	
	public AuthorObject searchBook(int author_id) {
		String sql = "SELECT * FROM tblauthor WHERE author_id = ? LIMIT 1";

		AuthorObject author = new AuthorObject();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, author_id);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					author.setAuthor_id(rs.getInt("author_id"));
					author.setAuthor_name(rs.getString("author_name"));
					author.setAuthor_date_of_birth(rs.getString("author_date_of_birth"));
					author.setAuthor_description(rs.getString("author_description"));
					author.setAuthor_image(rs.getString("author_image"));
					author.setCreated_at(rs.getString("created_at"));
					author.setUpdated_at(rs.getString("updated_at"));
					return author;
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

		return author;
	}
}
