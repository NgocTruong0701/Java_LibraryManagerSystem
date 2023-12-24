package it602003.process;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthorDelete
 */
@WebServlet("/author/delete")
public class AuthorDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {
			int authorID = Integer.parseInt(request.getParameter("id"));
			
			Author u = new Author();
			if (u.removeauthor(authorID)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Xóa tác giả thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/author"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể xóa tác giả, vui lòng thử lại');</script>");
			}
			
		}
		catch (Exception e) {
		    e.printStackTrace(); // In thông tin lỗi ra console
		    response.getWriter().println("<script>alert('Lỗi: " + e.getMessage() + "');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {
			int authorID = Integer.parseInt(request.getParameter("id"));
			
			Author u = new Author();
			if (u.removeauthor(authorID)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Xóa tác giả thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/author"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể xóa tác giả, vui lòng thử lại');</script>");
			}
			
		}
		catch (Exception e) {
		    e.printStackTrace(); // In thông tin lỗi ra console
		    response.getWriter().println("<script>alert('Lỗi: " + e.getMessage() + "');</script>");
		}
	}

}
