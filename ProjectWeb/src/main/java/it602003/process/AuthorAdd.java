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

import it602003.objects.AuthorObject;

/**
 * Servlet implementation class UserAddView
 */
@WebServlet("/author/create")
@MultipartConfig
public class AuthorAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorAdd() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/viewAddAuthor.jsp");
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
		String authorName = request.getParameter("author_name");
		String dateOfBirth = request.getParameter("author_date_of_birth");
		String description = request.getParameter("author_description");
		Part authorImage = request.getPart("author_image");

		if (authorImage != null) {
			String fileName = Paths.get(authorImage.getSubmittedFileName()).getFileName().toString(); // Tên file gốc
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
				fileContent = authorImage.getInputStream();

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

			Author a = new Author(); // Đối tượng User process để thực hiện các tác vụ thêm
			AuthorObject authorNew = new AuthorObject(); // Đối tượng User Object để lưu trữ dữ liệu từ form sau đó dùng User
													// process để thêm dữ liệu vào DB

			authorNew.setAuthor_name(authorName);
			authorNew.setAuthor_date_of_birth(dateOfBirth);
			authorNew.setAuthor_description(description);
			authorNew.setAuthor_image(fileName);
			
			if (a.addAuthor(authorNew)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.sendRedirect(request.getContextPath() + "/author"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể thêm người dùng, vui lòng thử lại');</script>");
			}
		} else {
			response.getWriter().println("<script>alert('Vui lòng thêm image');</script>");
			response.sendRedirect(request.getContextPath() + "/author/create"); // chuyển trang tới view list user
		}
	}

}
