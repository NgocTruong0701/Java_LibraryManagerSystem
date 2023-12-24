package it602003.process;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
//import it602003.objects.BookObject;
import it602003.objects.PublisherObject;
//import it602003.objects.SectionObject;

public class Publisher {
	// kết nối để làm việc với cơ sở dữ liệu
		private Connection con;

		// Bộ quản lý kết nối của riêng section
		private ConnectionPool cp;

		public Publisher() {
			// xác định bộ quản lý kết nối
			this.cp = new ConnectionPoolImpl();

			// Xin kết nối để làm việc
			try {
				this.con = this.cp.getConnection("Publisher");

				// Kiểm tra chế độ thực thi của kết nối
				if(this.con.getAutoCommit()) {
					// Hủy chế độ thực thi tự động
					this.con.setAutoCommit(false);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public ArrayList<PublisherObject> getPublisherObjects(PublisherObject similar, byte total){

			ArrayList<PublisherObject> items = new ArrayList<>();
			PublisherObject item;

			String sql = "SELECT * FROM tblpublisher ORDER BY publisher_name ASC LIMIT ?";

			// Biên dịch
//			Statement sta = this.con.createStatement();
//			sta.executeQuery(sql);
//			CallableStatement call = this.con.prepareCall(sql); có 3 cách dùng cách kia

			try {
				PreparedStatement pre = this.con.prepareStatement(sql);

				// Truyền giá trị cho tham số, tổng số bản ghi
				pre.setByte(1, total);

				ResultSet rs = pre.executeQuery(); // lấy về tập kết quả

				if(rs != null) {
					while(rs.next()) {
						item = new PublisherObject();
						item.setPublisher_id(rs.getShort("publisher_id"));
						item.setPublisher_name(rs.getString("publisher_name"));
						item.setPublisher_phone_number(rs.getString("publisher_phone_number"));
						item.setPublisher_address(rs.getString("publisher_address"));
						item.setCreated_at(rs.getString("created_at"));
						item.setUpdated_at(rs.getString("updated_at"));

						// Đưa vào tập hợp
						items.add(item);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// Trở về trạng thái an toàn của kết nối
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}


			return items;
		}
		public boolean addPublisher(PublisherObject item) {
			StringBuilder sql = new StringBuilder();
			 sql.append("INSERT INTO tblpublisher (publisher_name, publisher_phone_number, publisher_address) VALUES (?, ?, ?)");


			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Tạo câu lệnh để biên dịch
				pre.setString(1, item.getPublisher_name());
				pre.setString(2, item.getPublisher_phone_number());
				pre.setString(3, item.getPublisher_address());

				// thực thi - ở vùng rollback, vùng kết nối mềm để xem câu lệnh sql này có vấn đề này không, nếu ổn thì mới commit
				int result = pre.executeUpdate();
				if(result == 0)
				{
					// rollback lại trạng thái an toàn
					this.con.rollback();
					return false;
				}

				// Ghi nhận thực thi sau cùng
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
		public boolean editPublisher(PublisherObject publisher) {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE tblpublisher SET publisher_name = ?, publisher_phone_number= ? , publisher_address = ? "
					+ " WHERE publisher_id = ? ");
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());

				pre.setString(1, publisher.getPublisher_name());
				pre.setString(2, publisher.getPublisher_phone_number());
				pre.setString(3, publisher.getPublisher_address());
				pre.setInt(4, publisher.getPublisher_id());
								
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
		
		// Remove Publisher
		public boolean removePublisher(int publisher_id) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tblpublisher WHERE publisher_id = ?");
			
			try {
				PreparedStatement pre = this.con.prepareStatement(sql.toString());

				pre.setInt(1, publisher_id);

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
		
		
		// Search Publisher
		public PublisherObject searchPublisher(int publisher_id) {
			String sql = "SELECT * FROM tblpublisher WHERE publisher_id = ? LIMIT 1";

			PublisherObject publisher = new PublisherObject();

			try {
				PreparedStatement pre = this.con.prepareStatement(sql);

				pre.setInt(1, publisher_id);

				ResultSet rs = pre.executeQuery();
				if (rs != null) {
					if (rs.next()) {
						publisher.setPublisher_id(rs.getInt("publisher_id"));
						publisher.setPublisher_name(rs.getString("publisher_name"));
						publisher.setPublisher_phone_number(rs.getString("publisher_phone_number"));
						publisher.setPublisher_address(rs.getString("publisher_address"));
						publisher.setCreated_at(rs.getString("created_at")); 
						publisher.setUpdated_at(rs.getString("updated_at"));
						return publisher;
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
		
		public ArrayList<PublisherObject> searchPublisher(String publisher_name) {
			String sql = "SELECT * FROM tblpublisher WHERE publisher_name LIKE CONCAT('%', ?, '%')";

			ArrayList<PublisherObject> publishers = new ArrayList<PublisherObject>();

			try {
				PreparedStatement pre = this.con.prepareStatement(sql);

				pre.setString(1, publisher_name);

				ResultSet rs = pre.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						PublisherObject publisher = new PublisherObject();
						publisher.setPublisher_id(rs.getInt("publisher_id"));
						publisher.setPublisher_name(rs.getString("publisher_name"));
						publisher.setPublisher_phone_number(rs.getString("publisher_phone_number"));
						publisher.setPublisher_address(rs.getString("publisher_address"));
						publisher.setCreated_at(rs.getString("created_at")); 
						publisher.setUpdated_at(rs.getString("updated_at"));
						publishers.add(publisher);
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
			return publishers;
		}
		
		public ArrayList<PublisherObject> getAllPublishers() {
	        ArrayList<PublisherObject> publishers = new ArrayList<>();

	        String sql = "SELECT * FROM tblpublisher ORDER BY publisher_name ASC";

	        try {
	            PreparedStatement pre = this.con.prepareStatement(sql);
	            ResultSet rs = pre.executeQuery();

	            while (rs.next()) {
	                PublisherObject publisher = new PublisherObject();
	                publisher.setPublisher_id(rs.getInt("publisher_id"));
	                publisher.setPublisher_name(rs.getString("publisher_name"));
	                publisher.setPublisher_phone_number(rs.getString("publisher_phone_number"));
	                publisher.setPublisher_address(rs.getString("publisher_address"));
	                publisher.setCreated_at(rs.getString("created_at"));
	                publisher.setUpdated_at(rs.getString("updated_at"));
	                publishers.add(publisher);
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

	        return publishers;
	    }
		
//		public static void main(String[] args) {
//			// tạo đối tượng làm việc với Section
//			Publisher s = new Publisher();
//			
//
//			// Lấy danh sách đối tượng
//						PublisherObject publisherObject = new PublisherObject();
//			publisherObject.setPublisher_id(2);
//			publisherObject.setPublisher_name("Nguyễn");
//			publisherObject.setPublisher_phone_number("0921843966");
//			publisherObject.setPublisher_address("Bắc Giang");
//			//s.addPublisher(publisherObject);
//			//s.editPublisher(publisherObject);
//			//s.removePublisher(2);
//			ArrayList<PublisherObject> list =  s.searchPublisher("Mười");
//			list.forEach(item -> {
//				System.out.println(item);
//			});
//		}
}
//Publisher