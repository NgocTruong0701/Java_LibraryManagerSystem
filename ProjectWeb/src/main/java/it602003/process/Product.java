package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.ProductObject;

public class Product {
	// Kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng product
	private ConnectionPool cp;

	public Product() {
		// Xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = cp.getConnection("Product");

			// Hủy chế độ auto commit
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get danh sách Product
	public ArrayList<ProductObject> getProductObjects(ProductObject similar, byte total) {
		ArrayList<ProductObject> products = new ArrayList<>();
		ProductObject product;

		String sql = "SELECT * FROM tblproduct ORDER BY product_id ASC LIMIT ?"; // Khởi tạo truy vấn SQL

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ
																	// Connection và câu lệnh SQL được chuẩn bị

			// sau khi biên dịch thì truyền tham số vào
			pre.setByte(1, total); // Thiết lập giá trị cho cho tham số trong câu lệnh truy vấn

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về

			if (rs != null) {
				while (rs.next()) {
					product = new ProductObject();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_enable(rs.getBoolean("product_enable"));
					product.setProduct_delete(rs.getBoolean("product_delete"));
					product.setProduct_visited(rs.getShort("product_visited"));
					product.setProduct_total(rs.getShort("product_total"));
					product.setProduct_manager_id(rs.getInt("product_manager_id"));
					product.setProduct_intro(rs.getString("product_intro"));
					product.setProduct_notes(rs.getString("product_notes"));
					product.setProduct_code(rs.getString("product_code"));
					product.setProduct_created_date(rs.getString("product_created_date"));
					product.setProduct_modified_date(rs.getString("product_modified_date"));
					product.setProduct_pc_id(rs.getShort("product_pc_id"));
					product.setProduct_pg_id(rs.getShort("product_pg_id"));
					product.setProduct_ps_id(rs.getShort("product_ps_id"));
					product.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					product.setProduct_deleted_date(rs.getString("product_deleted_date"));
					product.setProduct_deleted_author(rs.getString("product_deleted_author"));
					product.setProduct_promotion_price(rs.getInt("product_promotion_price"));
					product.setProduct_sold(rs.getShort("product_sold"));
					product.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					product.setProduct_promotion(rs.getBoolean("product_promotion"));
					product.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_name_en(rs.getString("product_name_en"));
					product.setProduct_customer_id(rs.getInt("product_customer_id"));
					product.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));

					// Add product vào list
					products.add(product);
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

		return products;
	}

	// Add Product
	public boolean addProduct(ProductObject product) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblproduct (product_name, product_image, product_price, product_discount_price, ");
		sql.append(
				"product_enable, product_delete, product_visited, product_total, product_manager_id, product_intro, ");
		sql.append(
				"product_notes, product_code, product_created_date, product_modified_date, product_pc_id, product_pg_id, ");
		sql.append(
				"product_ps_id, product_is_detail, product_deleted_date, product_deleted_author, product_promotion_price, ");
		sql.append(
				"product_sold, product_best_seller, product_promotion, product_price_calc_description, product_size, ");
		sql.append("product_name_en, product_customer_id, product_perspective_id)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Biên dịch câu lệnh SQL

			// Set value cho các tham số
			pre.setString(1, product.getProduct_name());
			pre.setString(2, product.getProduct_image());
			pre.setInt(3, product.getProduct_price());
			pre.setInt(4, product.getProduct_discount_price());
			pre.setBoolean(5, product.isProduct_enable());
			pre.setBoolean(6, product.isProduct_delete());
			pre.setShort(7, product.getProduct_visited());
			pre.setShort(8, product.getProduct_total());
			pre.setInt(9, product.getProduct_manager_id());
			pre.setString(10, product.getProduct_intro());
			pre.setString(11, product.getProduct_notes());
			pre.setString(12, product.getProduct_code());
			pre.setString(13, product.getProduct_created_date());
			pre.setString(14, product.getProduct_modified_date());
			pre.setShort(15, product.getProduct_pc_id());
			pre.setShort(16, product.getProduct_pg_id());
			pre.setShort(17, product.getProduct_ps_id());
			pre.setBoolean(18, product.isProduct_is_detail());
			pre.setString(19, product.getProduct_deleted_date());
			pre.setString(20, product.getProduct_deleted_author());
			pre.setInt(21, product.getProduct_promotion_price());
			pre.setShort(22, product.getProduct_sold());
			pre.setBoolean(23, product.isProduct_best_seller());
			pre.setBoolean(24, product.isProduct_promotion());
			pre.setShort(25, product.getProduct_price_calc_description());
			pre.setString(26, product.getProduct_size());
			pre.setString(27, product.getProduct_name_en());
			pre.setInt(28, product.getProduct_customer_id());
			pre.setBoolean(29, product.isProduct_perspective_id());

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

	// Edit Product
	public boolean editProduct(ProductObject product) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"UPDATE tblproduct SET product_name=?, product_image=?, product_price=?, product_discount_price=?, ");
		sql.append("product_enable=?, product_delete=?, product_visited=?, product_total=?, product_manager_id=?, ");
		sql.append(
				"product_intro=?, product_notes=?, product_code=?, product_created_date=?, product_modified_date=?, ");
		sql.append("product_pc_id=?, product_pg_id=?, product_ps_id=?, product_is_detail=?, product_deleted_date=?, ");
		sql.append("product_deleted_author=?, product_promotion_price=?, product_sold=?, product_best_seller=?, ");
		sql.append("product_promotion=?, product_price_calc_description=?, product_size=?, product_name_en=?, ");
		sql.append("product_customer_id=?, product_perspective_id=? WHERE product_id=?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setString(1, product.getProduct_name());
			pre.setString(2, product.getProduct_image());
			pre.setInt(3, product.getProduct_price());
			pre.setInt(4, product.getProduct_discount_price());
			pre.setBoolean(5, product.isProduct_enable());
			pre.setBoolean(6, product.isProduct_delete());
			pre.setShort(7, product.getProduct_visited());
			pre.setShort(8, product.getProduct_total());
			pre.setInt(9, product.getProduct_manager_id());
			pre.setString(10, product.getProduct_intro());
			pre.setString(11, product.getProduct_notes());
			pre.setString(12, product.getProduct_code());
			pre.setString(13, product.getProduct_created_date());
			pre.setString(14, product.getProduct_modified_date());
			pre.setShort(15, product.getProduct_pc_id());
			pre.setShort(16, product.getProduct_pg_id());
			pre.setShort(17, product.getProduct_ps_id());
			pre.setBoolean(18, product.isProduct_is_detail());
			pre.setString(19, product.getProduct_deleted_date());
			pre.setString(20, product.getProduct_deleted_author());
			pre.setInt(21, product.getProduct_promotion_price());
			pre.setShort(22, product.getProduct_sold());
			pre.setBoolean(23, product.isProduct_best_seller());
			pre.setBoolean(24, product.isProduct_promotion());
			pre.setShort(25, product.getProduct_price_calc_description());
			pre.setString(26, product.getProduct_size());
			pre.setString(27, product.getProduct_name_en());
			pre.setInt(28, product.getProduct_customer_id());
			pre.setBoolean(29, product.isProduct_perspective_id());
			pre.setInt(30, product.getProduct_id());

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

	// Remove Product
	public boolean removeProduct(int product_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblproduct WHERE product_id = ?");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			pre.setInt(1, product_id);

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

	// Search Product
	public ProductObject searchProduct(int product_id) {
		String sql = "SELECT * FROM tblproduct WHERE product_id = ? LIMIT 1";

		ProductObject product = new ProductObject();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setInt(1, product_id);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_enable(rs.getBoolean("product_enable"));
					product.setProduct_delete(rs.getBoolean("product_delete"));
					product.setProduct_visited(rs.getShort("product_visited"));
					product.setProduct_total(rs.getShort("product_total"));
					product.setProduct_manager_id(rs.getInt("product_manager_id"));
					product.setProduct_intro(rs.getString("product_intro"));
					product.setProduct_notes(rs.getString("product_notes"));
					product.setProduct_code(rs.getString("product_code"));
					product.setProduct_created_date(rs.getString("product_created_date"));
					product.setProduct_modified_date(rs.getString("product_modified_date"));
					product.setProduct_pc_id(rs.getShort("product_pc_id"));
					product.setProduct_pg_id(rs.getShort("product_pg_id"));
					product.setProduct_ps_id(rs.getShort("product_ps_id"));
					product.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					product.setProduct_deleted_date(rs.getString("product_deleted_date"));
					product.setProduct_deleted_author(rs.getString("product_deleted_author"));
					product.setProduct_promotion_price(rs.getInt("product_promotion_price"));
					product.setProduct_sold(rs.getShort("product_sold"));
					product.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					product.setProduct_promotion(rs.getBoolean("product_promotion"));
					product.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_name_en(rs.getString("product_name_en"));
					product.setProduct_customer_id(rs.getInt("product_customer_id"));
					product.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));

					return product;
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

	public ArrayList<ProductObject> searchProduct(String product_name) {
		String sql = "SELECT * FROM tblproduct WHERE product_name LIKE CONCAT('%', ?, '%')";

		ArrayList<ProductObject> products = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			pre.setString(1, product_name);

			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ProductObject product = new ProductObject();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_enable(rs.getBoolean("product_enable"));
					product.setProduct_delete(rs.getBoolean("product_delete"));
					product.setProduct_visited(rs.getShort("product_visited"));
					product.setProduct_total(rs.getShort("product_total"));
					product.setProduct_manager_id(rs.getInt("product_manager_id"));
					product.setProduct_intro(rs.getString("product_intro"));
					product.setProduct_notes(rs.getString("product_notes"));
					product.setProduct_code(rs.getString("product_code"));
					product.setProduct_created_date(rs.getString("product_created_date"));
					product.setProduct_modified_date(rs.getString("product_modified_date"));
					product.setProduct_pc_id(rs.getShort("product_pc_id"));
					product.setProduct_pg_id(rs.getShort("product_pg_id"));
					product.setProduct_ps_id(rs.getShort("product_ps_id"));
					product.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					product.setProduct_deleted_date(rs.getString("product_deleted_date"));
					product.setProduct_deleted_author(rs.getString("product_deleted_author"));
					product.setProduct_promotion_price(rs.getInt("product_promotion_price"));
					product.setProduct_sold(rs.getShort("product_sold"));
					product.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					product.setProduct_promotion(rs.getBoolean("product_promotion"));
					product.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_name_en(rs.getString("product_name_en"));
					product.setProduct_customer_id(rs.getInt("product_customer_id"));
					product.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));
					products.add(product);
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
		return products;
	}

	// Get total Product Count
	public int getTotalProductCount() {
		String sql = "SELECT COUNT(*) FROM tblproduct";

		int count = 0;
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					count = rs.getInt(1);
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
		return count;
	}

	// Sum price of products
	public int getTotalPriceOfProducts() {
		String sql = "SELECT SUM(product_price * product_sold) FROM tblproduct";
		int totalPrice = 0;

		try (PreparedStatement pre = this.con.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
			if (rs.next()) {
				totalPrice = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					this.con.rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}

		return totalPrice;
	}

	// Get Top 10 Product best sell
	// Get danh sách Product
	public ArrayList<ProductObject> getTopProductBestSell() {
		ArrayList<ProductObject> products = new ArrayList<>();
		ProductObject product;

		String sql = "SELECT * FROM tblproduct ORDER BY product_sold DESC LIMIT 10"; // Khởi tạo truy vấn SQL

		// Dùng PreparedStatement để truyền tham số vào và có thể dùng nhiều lần
		try {
			PreparedStatement pre = this.con.prepareStatement(sql); // Biên dịch câu truy vấn SQL. Được tạo từ

			ResultSet rs = pre.executeQuery(); // Lấy tập kết quả trả về

			if (rs != null) {
				while (rs.next()) {
					product = new ProductObject();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setProduct_image(rs.getString("product_image"));
					product.setProduct_price(rs.getInt("product_price"));
					product.setProduct_discount_price(rs.getInt("product_discount_price"));
					product.setProduct_enable(rs.getBoolean("product_enable"));
					product.setProduct_delete(rs.getBoolean("product_delete"));
					product.setProduct_visited(rs.getShort("product_visited"));
					product.setProduct_total(rs.getShort("product_total"));
					product.setProduct_manager_id(rs.getInt("product_manager_id"));
					product.setProduct_intro(rs.getString("product_intro"));
					product.setProduct_notes(rs.getString("product_notes"));
					product.setProduct_code(rs.getString("product_code"));
					product.setProduct_created_date(rs.getString("product_created_date"));
					product.setProduct_modified_date(rs.getString("product_modified_date"));
					product.setProduct_pc_id(rs.getShort("product_pc_id"));
					product.setProduct_pg_id(rs.getShort("product_pg_id"));
					product.setProduct_ps_id(rs.getShort("product_ps_id"));
					product.setProduct_is_detail(rs.getBoolean("product_is_detail"));
					product.setProduct_deleted_date(rs.getString("product_deleted_date"));
					product.setProduct_deleted_author(rs.getString("product_deleted_author"));
					product.setProduct_promotion_price(rs.getInt("product_promotion_price"));
					product.setProduct_sold(rs.getShort("product_sold"));
					product.setProduct_best_seller(rs.getBoolean("product_best_seller"));
					product.setProduct_promotion(rs.getBoolean("product_promotion"));
					product.setProduct_price_calc_description(rs.getShort("product_price_calc_description"));
					product.setProduct_size(rs.getString("product_size"));
					product.setProduct_name_en(rs.getString("product_name_en"));
					product.setProduct_customer_id(rs.getInt("product_customer_id"));
					product.setProduct_perspective_id(rs.getBoolean("product_perspective_id"));

					// Add product vào list
					products.add(product);
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

		return products;
	}

	// AVG Price of Products
	public double getAveragePriceOfProducts() {
		String sql = "SELECT AVG(product_price) FROM tblproduct";
		double averagePrice = 0;

		try (PreparedStatement pre = this.con.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
			if (rs.next()) {
				averagePrice = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return averagePrice;
	}

	// Max price of products
	public int getMaxPriceOfProduct() {
		String sql = "SELECT MAX(product_price) FROM tblproduct";
		int maxPrice = 0;

		try (PreparedStatement pre = this.con.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
			if (rs.next()) {
				maxPrice = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return maxPrice;
	}

	// Min price of products
	public int getMinPriceOfProduct() {
		String sql = "SELECT MIN(product_price) FROM tblproduct";
		int minPrice = 0;

		try (PreparedStatement pre = this.con.prepareStatement(sql); ResultSet rs = pre.executeQuery()) {
			if (rs.next()) {
				minPrice = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return minPrice;
	}

	public static void main(String[] args) {
		Product p = new Product();

//	    // Thêm sản phẩm
//	    ProductObject newProduct = new ProductObject();
//	    newProduct.setProduct_name("Product Name");
//	    newProduct.setProduct_image("image.jpg");
//	    newProduct.setProduct_price(100);
//	    // Thiết lập các thuộc tính khác của sản phẩm
//	    boolean isProductAdded = p.addProduct(newProduct);
//	    System.out.println("Sản phẩm đã được thêm vào cơ sở dữ liệu: " + isProductAdded);

		// Cập nhật sản phẩm (giả sử productToUpdate có thông tin được lấy từ cơ sở dữ
		// liệu)
//		ProductObject productToUpdate = new ProductObject();
//		productToUpdate.setProduct_id(1); // Đặt ID của sản phẩm cần cập nhật
//		// Thiết lập các thuộc tính cần cập nhật của sản phẩm
//		productToUpdate.setProduct_name("Product Name 2");
//		productToUpdate.setProduct_image("image.jpg");
//		boolean isProductUpdated = p.editProduct(productToUpdate);
//		System.out.println("Sản phẩm đã được cập nhật trong cơ sở dữ liệu: " + isProductUpdated);

//	    // Xóa sản phẩm (giả sử productIdToDelete được lấy từ đầu vào hoặc cơ sở dữ liệu)
//	    int productIdToDelete = 1;
//	    boolean isProductDeleted = p.removeProduct(productIdToDelete);
//	    System.out.println("Sản phẩm đã được xóa khỏi cơ sở dữ liệu: " + isProductDeleted);

//		 Lấy danh sách sản phẩm (giả sử getListOfProducts trả về một danh sách sản
//		 phẩm)
//		ArrayList<ProductObject> listOfProducts = p.getProductObjects(null, (byte) 10);
//		System.out.println("Danh sách sản phẩm từ cơ sở dữ liệu:");
//		for (ProductObject product : listOfProducts) {
//			System.out.println(product.toString());
//		}

		System.out.println("Count: " + p.getTotalProductCount());
	}
}
