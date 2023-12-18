package it602003.process;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
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
			int userID = Integer.parseInt(request.getParameter("id"));
			
			User u = new User();
			if (u.removeUser(userID)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Xóa người dùng thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/user"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể xóa người dùng, vui lòng thử lại');</script>");
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
			int userID = Integer.parseInt(request.getParameter("id"));
			
			User u = new User();
			if (u.removeUser(userID)) {
				// response.getWriter() để lấy đối tượng PrintWriter
				response.getWriter().println("<script>alert('Xóa người dùng thành công');</script>");
				response.sendRedirect(request.getContextPath() + "/user"); // chuyển trang tới view list user
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể xóa người dùng, vui lòng thử lại');</script>");
			}
			
		}
		catch (Exception e) {
		    e.printStackTrace(); // In thông tin lỗi ra console
		    response.getWriter().println("<script>alert('Lỗi: " + e.getMessage() + "');</script>");
		}
	}

}
