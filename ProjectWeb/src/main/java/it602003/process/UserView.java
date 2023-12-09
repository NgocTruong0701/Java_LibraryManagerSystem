package it602003.process;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserView
 */
@WebServlet("/user/")
public class UserView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserView() {
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
		String pathInfo = request.getServletPath();  // Lấy thông tin đường dẫn sau /user/

		if (pathInfo != null) {
			String[] pathParts = pathInfo.split("/"); // Tách đường dẫn thông qua / thành các phần

			if (pathParts.length > 0) {
				String action = pathParts[1]; // Lấy phần thứ hai của đường dẫn

				switch (action) {
					case "create":
						request.getRequestDispatcher("/WEB-INF/view/viewUser.jsp").forward(request, response);
						break;
					case "edit":
						request.getRequestDispatcher("/WEB-INF/view/viewUser.jsp").forward(request, response);
						break;
					default:
						request.getRequestDispatcher("/WEB-INF/view/viewUser.jsp").forward(request, response);
						break;
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
