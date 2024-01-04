package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.BookObject;
import it602003.objects.CategoryObject;

public class Book {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng Book
	private ConnectionPool cp;

	public Book() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("book");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BookObject> getBookObjects(BookObject similar, byte total) {
		ArrayList<BookObject> books = new ArrayList<>();
		BookObject book;

		String sql = "SELECT * FROM tblbook ORDER BY book_id ASC LIMIT ?"; // Khởi tạo truy vấn SQL

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị

			// sau khi biên dịch thì truyền tham số vào
			pre.setByte(1, total); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về

			if (rs != null) {
				while (rs.next()) {
					book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					// Add Book vào list
					books.add(book);
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

		return books;
	}

	// Add Book
	public boolean addBook(BookObject book) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO tblbook (book_name, book_publishing_year," + " book_image, book_description, book_price,"
						+ " book_inventory_number, book_page_number, book_status, "
						+ "book_language, author_id, category_id, " + "publisher_id, created_at, updated_at)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			// Set value cho các tham số
			pre.setString(1, book.getBook_name());
			pre.setString(2, book.getBook_publishing_year());
			pre.setString(3, book.getBook_image());
			pre.setString(4, book.getBook_description());
			pre.setFloat(5, book.getBook_price());
			pre.setInt(6, book.getBook_inventory_number());
			pre.setInt(7, book.getBook_page_number());
			pre.setString(8, book.getBook_status());
			pre.setString(9, book.getBook_language());
			pre.setInt(10, book.getAuthor_id());
			pre.setInt(11, book.getCategory_id());
			pre.setInt(12, book.getPublisher_id());
			pre.setString(13, book.getCreated_at());
			pre.setString(14, book.getUpdated_at());

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

	// Edit Book
	public boolean editBook(BookObject book) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE tblbook SET book_name =?, book_publishing_year=?, book_image=?, book_description=?, book_price=?, "
						+ "book_inventory_number=?, book_page_number=?, book_status=?, book_language=?, author_id=?, "
						+ "category_id=?, publisher_id=? " + " WHERE book_id = ? ");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, book.getBook_name());
			pre.setString(2, book.getBook_publishing_year());
			pre.setString(3, book.getBook_image());
			pre.setString(4, book.getBook_description());
			pre.setFloat(5, book.getBook_price());
			pre.setInt(6, book.getBook_inventory_number());
			pre.setInt(7, book.getBook_page_number());
			pre.setString(8, book.getBook_status());
			pre.setString(9, book.getBook_language());
			pre.setInt(10, book.getAuthor_id());
			pre.setInt(11, book.getCategory_id());
			pre.setInt(12, book.getPublisher_id());
			pre.setInt(13, book.getBook_id());

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

	// Remove Book
	public boolean removeBook(int book_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblbook WHERE book_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, book_id);

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

	// Search Book
	public BookObject searchBook(int book_id) {
		String sql = "SELECT * FROM tblbook WHERE book_id = ? LIMIT 1";

		BookObject book = new BookObject();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, book_id);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					return book;
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

	public ArrayList<BookObject> searchBook(String book_name) {
		String sql = "SELECT * FROM tblbook WHERE book_name LIKE CONCAT('%', ?, '%')";

		ArrayList<BookObject> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, book_name);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					BookObject book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					books.add(book);
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
		return books;
	}

	public ArrayList<BookObject> getBooksByCategory(int categoryId, byte total) {
		ArrayList<BookObject> books = new ArrayList<>();
		BookObject book;

		String sql = "SELECT * FROM tblbook WHERE category_id = ? ORDER BY book_id ASC LIMIT ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, categoryId);
			pre.setByte(2, total);

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));

					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<BookObject> getBooksByCategoryWithPagination(int categoryId, int startIndex, int pageSize) {
		ArrayList<BookObject> books = new ArrayList<>();
		BookObject book;

		String sql = "SELECT * FROM tblbook WHERE category_id = ? ORDER BY book_id ASC LIMIT ?, ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, categoryId);
			pre.setInt(2, startIndex);
			pre.setInt(3, pageSize);

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));

					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<BookObject> getNewestBooks(int limit) {
		ArrayList<BookObject> books = new ArrayList<>();
		BookObject book;

		String sql = "SELECT * FROM tblbook ORDER BY created_at DESC LIMIT ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, limit);

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));

					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<BookObject> searchBooksByAuthorId(int authorId) {
		String sql = "SELECT * FROM tblbook WHERE author_id = ?";

		ArrayList<BookObject> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, authorId);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					BookObject book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));

					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return books;
	}

	public ArrayList<BookObject> searchBooksByPublisherName(String publisherName) {
		String sql = "SELECT * FROM tblbook WHERE publisher_id IN (SELECT publisher_id FROM tblpublisher WHERE publisher_name LIKE CONCAT('%', ?, '%'))";

		ArrayList<BookObject> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, publisherName);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					BookObject book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));
					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<BookObject> searchBooksByAuthorName(String authorName) {
		String sql = "SELECT * FROM tblbook WHERE author_id IN (SELECT author_id FROM tblauthor WHERE author_name LIKE ?)";

		ArrayList<BookObject> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, "%" + authorName + "%");

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					BookObject book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));
					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<BookObject> searchBooks(String keyword) {
		String sql = "SELECT * FROM tblbook WHERE book_name LIKE ? OR author_id IN (SELECT author_id FROM tblauthor WHERE author_name LIKE ?) OR publisher_id IN (SELECT publisher_id FROM tblpublisher WHERE publisher_name LIKE ?)";

		ArrayList<BookObject> books = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, "%" + keyword + "%");
			pre.setString(2, "%" + keyword + "%");
			pre.setString(3, "%" + keyword + "%");

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					BookObject book = new BookObject();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_name(rs.getString("book_name"));
					book.setBook_publishing_year(rs.getString("book_publishing_year"));
					book.setBook_image(rs.getString("book_image"));
					book.setBook_description(rs.getString("book_description"));
					book.setBook_price(rs.getFloat("book_price"));
					book.setBook_inventory_number(rs.getInt("book_inventory_number"));
					book.setBook_page_number(rs.getInt("book_page_number"));
					book.setBook_status(rs.getString("book_status"));
					book.setBook_language(rs.getString("book_language"));
					book.setAuthor_id(rs.getInt("author_id"));
					;
					book.setCategory_id(rs.getInt("category_id"));
					book.setPublisher_id(rs.getInt("publisher_id"));
					book.setCreated_at(rs.getString("created_at"));
					book.setUpdated_at(rs.getString("updated_at"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return books;
	}

	public int getTotalBooks() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM tblbook");
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

	public boolean updateInventoryNumber(int id, int number) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE tblbook SET book_inventory_number=? WHERE book_id = ? ");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, number);
			pre.setInt(2, id);

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
	
	public static void main(String[] args) {
		Book book = new Book();

//	    // Hiển thị kết quả từ getBookObjects
//	    ArrayList<BookObject> arrayList = book.getBookObjects(null, (byte) 100);
//	    arrayList.forEach(book2 -> {
//	        System.out.println(book2.toString());
//	    });
//	    
//	    BookObject book1 = new BookObject();
//	    book1.setBook_name("Tiếng Anh 2");
//	    book1.setAuthor_id(1);
//	    book1.setPublisher_id(1);
//	    book1.setCategory_id(1);
//	    for(int i=0; i<40; i++) {
//	    	if(book.addBook(book1)) {
//		    	System.out.println("Thêm thành công");
//		    }else {
//		    	System.out.println("Thêm không thành công");
//		    }
//	    }
//	    
//	    System.out.println(book.searchBook((int)157));
		int categoryId = 1; // Thay thế bằng categoryId thực tế
		int startIndex = 0; // Vị trí bắt đầu
		int pageSize = 10; // Số lượng sách trên mỗi trang

		ArrayList<BookObject> arrayList1 = book.searchBooksByPublisherName("Nhà xuất bản Đại học Quốc Gia ");
		arrayList1.forEach(list -> {
			System.out.println(list.toString());
		});
//		ArrayList<BookObject> arrayList = book.getBooksByCategoryWithPagination(categoryId, startIndex, pageSize);
//		arrayList.forEach(listbook ->{
//			System.out.println(listbook.toString());
//		});
	}

}
