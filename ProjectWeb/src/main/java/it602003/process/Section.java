package it602003.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it602003.ConnectionPool;
import it602003.ConnectionPoolImpl;
import it602003.objects.SectionObject;

public class Section {
	// kết nối để làm việc với cơ sở dữ liệu
	private Connection con;

	// Bộ quản lý kết nối của riêng section
	private ConnectionPool cp;

	public Section() {
		// xác định bộ quản lý kết nối
		this.cp = new ConnectionPoolImpl();

		// Xin kết nối để làm việc
		try {
			this.con = this.cp.getConnection("Section");

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

	public ArrayList<SectionObject> getSectionObjects(SectionObject similar, byte total){

		ArrayList<SectionObject> items = new ArrayList<>();
		SectionObject item;

		String sql = "SELECT * FROM tblsection ORDER BY section_name ASC LIMIT ?";

		// Biên dịch
//		Statement sta = this.con.createStatement();
//		sta.executeQuery(sql);
//		CallableStatement call = this.con.prepareCall(sql); có 3 cách dùng cách kia

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);

			// Truyền giá trị cho tham số, tổng số bản ghi
			pre.setByte(1, total);

			ResultSet rs = pre.executeQuery(); // lấy về tập kết quả

			if(rs != null) {
				while(rs.next()) {
					item = new SectionObject();
//					item.setSection_id(rs.getShort(1));
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					item.setSection_notes(rs.getString("section_notes"));
					item.setSection_created_date(rs.getString("section_created_date"));
					item.setSection_manager_id(rs.getInt("section_manager_id"));
					item.setSection_name_en(rs.getString("section_name_en"));

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

	public boolean addSection(SectionObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblsection(");
		sql.append("section_name, section_notes, section_created_date,");
		sql.append("section_manager_id, section_enable, section_delete, section_last_modified,");
		sql.append("section_created_author_id, section_name_en, section_language) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString()); // Tạo câu lệnh để biên dịch
			// add value
			pre.setString(1, item.getSection_name());
			pre.setString(2, item.getSection_notes());
			pre.setString(3, item.getSection_created_date());
			pre.setInt(4, item.getSection_manager_id());
			pre.setBoolean(5, item.isSection_enable());
			pre.setBoolean(6, item.isSection_delete());
			pre.setString(7, item.getSection_last_modified());
			pre.setInt(8, item.getSection_created_author_id());
			pre.setString(9, item.getSection_name_en());
			pre.setByte(10, item.getSection_language());

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

	public static void main(String[] args) {
		// tạo đối tượng làm việc với Section
		Section s = new Section();


//		// Tạo đối tượng Chuyên mục mới
//		SectionObject nsec = new SectionObject();
//		nsec.setSection_name("LT Java Nang cao");
//		nsec.setSection_created_date("27/10/23");
//
////		if(!s.addSection(nsec)) {
////			System.out.println("-------KHÔNG THÀNH CÔNG----------");
////		}

		// Lấy danh sách đối tượng
		ArrayList<SectionObject> items = s.getSectionObjects(null, (byte)20);

		// In ra màn hình
		items.forEach(item -> {
			System.out.println(item);
		});
	}
}
