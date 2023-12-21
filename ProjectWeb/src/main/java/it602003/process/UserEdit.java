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
 * Servlet implementation class UserEdit
 */
@WebServlet("/user/update")
@MultipartConfig
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEdit() {
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
		request.setCharacterEncoding("UTF-8");
		try {
			int userID = Integer.parseInt(request.getParameter("id"));

			User u = new User();
			UserObject user = u.getuserObjectsById(0, userID);

			request.setAttribute("user_id", user.getUser_id()); // Truyền thông tin người dùng sang JSP
			request.setAttribute("user_name", user.getUser_name());
			request.setAttribute("user_image", user.getUser_image());
			request.setAttribute("user_phone_number", user.getUser_phone_number());
			request.setAttribute("user_address", user.getUser_address());
			request.setAttribute("user_account_name", user.getUser_account_name());
			request.setAttribute("user_password", user.getUser_account_password());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/viewEditUser.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(); // In thông tin lỗi ra console
			response.getWriter().println("<script>alert('Lỗi: " + e.getMessage() + "');</script>");
			response.sendRedirect(request.getContextPath() + "/user");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("user_name");
		Part userImage = request.getPart("user_image");
		String phoneNumber = request.getParameter("user_phone_number");
		String address = request.getParameter("user_address");
		String accountName = request.getParameter("user_account_name");
		String accountPassword = request.getParameter("user_account_password");
		String oldNameImage = request.getParameter("user_image_old");

		User u = new User(); // Đối tượng User process để thực hiện các tác vụ thêm
		UserObject userNew = new UserObject(); // Đối tượng User Object để lưu trữ dữ liệu từ form sau đó dùng
												// User
												// process để thêm dữ liệu vào DB
		try {
			int userID = Integer.parseInt(request.getParameter("user_id"));
			String fileName = Paths.get(userImage.getSubmittedFileName()).getFileName().toString();
			if(!fileName.isEmpty()) { 
				String uploadPath = getServletContext().getRealPath("/") + "image";
				File file = new File(uploadPath + File.separator + fileName); // File.separator dùng để lấy kí tự phân
																				// cách
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
				
				userNew.setUser_image(fileName);
			} else {
				userNew.setUser_image(oldNameImage);
			}

			userNew.setUser_name(userName);
			userNew.setUser_phone_number(phoneNumber);
			userNew.setUser_address(address);
			userNew.setUser_account_name(accountName);
			userNew.setUser_account_password(accountPassword);
			userNew.setUser_role(0);

			if (u.editUser(userID, userNew)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Sửa thông tin người dùng thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/user"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể thêm người dùng, vui lòng thử lại');</script>");
				response.sendRedirect(request.getContextPath() + "/user");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.getWriter()
			.println("<script>alert('Lỗi: Thông tin không hợp lệ, vui lòng thử lại');</script>");
			response.sendRedirect(request.getContextPath() + "/user");
		}
	}

}
