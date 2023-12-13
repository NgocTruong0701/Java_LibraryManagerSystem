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

import it602003.objects.UserObject;

/**
 * Servlet implementation class UserAddView
 */
@WebServlet("/user/create")
@MultipartConfig
public class UserAddView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/viewAddUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy dữ liệu từ trang JSP
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("user_name");
		Part userImage = request.getPart("user_image");
		String phoneNumber = request.getParameter("user_phone_number");
		String address = request.getParameter("user_address");
		String accountName = request.getParameter("user_account_name");
		String accountPassword = request.getParameter("user_account_password");

		if (userImage != null) {
			String fileName = Paths.get(userImage.getSubmittedFileName()).getFileName().toString(); // Tên file gốc
			String uploadPath = getServletContext().getRealPath("/image");
			File file = new File(uploadPath + File.separator + fileName); // File.separator dùng để lấy kí tự phân cách
																			// trên hệ điều hành
			// Lưu tập tin hình ảnh xuống
			OutputStream out = null;
			InputStream fileContent = null;
			
			System.out.println("Path folder image: " + uploadPath);

			try {
				// Tạo một OutputStream để ghi tập tin hình ảnh xuống ổ đĩa
				out = new FileOutputStream(file);
				fileContent = userImage.getInputStream();

				// Đọc dữ liệu từ InputStream của part và ghi xuống OutputStream để lưu tập tin
				// hình ảnh
				int read;
				final byte[] bytes = new byte[1024];
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

			User u = new User(); // Đối tượng User process để thực hiện các tác vụ thêm
			UserObject userNew = new UserObject(); // Đối tượng User Object để lưu trữ dữ liệu từ form sau đó dùng User
													// process để thêm dữ liệu vào DB

			userNew.setUser_name(userName);
			userNew.setUser_image(fileName);
			userNew.setUser_phone_number(phoneNumber);
			userNew.setUser_address(address);
			userNew.setUser_account_name(accountName);
			userNew.setUser_account_password(accountPassword);
			userNew.setUser_role(0);
			
			if (u.addUser(userNew)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Thêm người dùng thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/user"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể thêm người dùng, vui lòng thử lại');</script>");
			}
		} else {
			response.getWriter().println("<script>alert('Vui lòng thêm image');</script>");
			response.sendRedirect(request.getContextPath() + "/user/create"); // chuyển trang tới view list user
		}
	}

}
