package it602003.process;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import it602003.objects.BookObject;
/**
 * Servlet implementation class BookAddControl
 */
@WebServlet("/bookaddcontrol")
@MultipartConfig
public class BookAddControl extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAddControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/AddBook.jsp");
	    dispatcher.forward(request, response);
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Xử lý phần tải lên ảnh
		// Lấy dữ liệu từ trang JSP
				request.setCharacterEncoding("UTF-8");
				String tenSach = request.getParameter("tenSach");
				String namXuatBan = request.getParameter("namXuatBan");
				Part hinhAnh = request.getPart("hinhAnh");
				float gia = Float.parseFloat(request.getParameter("gia")) ;
				int soLuongTonKho = Integer.parseInt(request.getParameter("soLuongTonKho"));
				int soTrang = Integer.parseInt(request.getParameter("soTrang"));
				String tinhTrang = request.getParameter("tinhTrang");
				String ngonNgu = request.getParameter("ngonNgu");
				int maTacGia = Integer.parseInt(request.getParameter("maTacGia"));
				int maDanhMuc = Integer.parseInt(request.getParameter("maDanhMuc"));
				int maNhaXuatBan = Integer.parseInt(request.getParameter("maNhaXuatBan"));
				String moTa = request.getParameter("moTa");
				Category category = new Category();
				if (hinhAnh != null) {
					String fileName = Paths.get(hinhAnh.getSubmittedFileName()).getFileName().toString(); // Tên file gốc
					String uploadPath = getServletContext().getRealPath("/") + "image";
					File file = new File(uploadPath + File.separator + fileName); // File.separator dùng để lấy kí tự phân cách
																					// trên hệ điều hành
					// Lưu tập tin hình ảnh xuống
					OutputStream out = null;
					InputStream fileContent = null;
					
					System.out.println("Path folder image: " + uploadPath);

					try {
						// Tạo một OutputStream để ghi tập tin hình ảnh xuống ổ đĩa
						out = new FileOutputStream(file);
						fileContent = hinhAnh.getInputStream();

						// Đọc dữ liệu từ InputStream của part và ghi xuống OutputStream để lưu tập tin
						// hình ảnh
						int read;
						final byte[] bytes = new byte[fileContent.available()];
						while ((read = fileContent.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
					} catch (FileNotFoundException e) {
						 e.printStackTrace();
					} finally {
						if (out != null) {
							out.close();
						}
						if (fileContent != null) {
							fileContent.close();
						}
					}

					Book book = new Book(); // Đối tượng Book process để thực hiện các tác vụ thêm
					BookObject bookNew = new BookObject(); // Đối tượng BookObject để lưu trữ dữ liệu từ form sau đó dùng User
															// process để thêm dữ liệu vào DB

					bookNew.setBook_name(tenSach);
					bookNew.setBook_image(fileName);
					bookNew.setBook_publishing_year(namXuatBan);
					bookNew.setBook_price(gia);
					bookNew.setBook_inventory_number(soLuongTonKho);
					bookNew.setBook_page_number(soTrang);
					bookNew.setBook_status(tinhTrang);
					bookNew.setBook_language(ngonNgu);
					bookNew.setAuthor_id(maTacGia);
					bookNew.setCategory_id(maDanhMuc);
					bookNew.setPublisher_id(maNhaXuatBan);
					bookNew.setBook_description(moTa);
					
					if (book.addBook(bookNew)) {
						// response.getWriter() để lấy đối tượng PrintWriter
						category.capNhat(bookNew.getCategory_id());
						response.getWriter().println("<script>alert('Thêm sách thành công');</script>");
						response.sendRedirect(request.getContextPath() + "/bookview"); // chuyển trang tới view list user
						
					} else {
						response.getWriter()
								.println("<script>alert('Lỗi: Không thể thêm sách, vui lòng thử lại');</script>");
					}
				} else {
					response.getWriter().println("<script>alert('Vui lòng thêm image');</script>");
					response.sendRedirect(request.getContextPath() + "/bookaddcontrol"); // chuyển trang tới view list user
				}
				
	}
}
