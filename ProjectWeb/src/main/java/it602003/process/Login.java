package it602003.process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it602003.objects.UserObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Kiểm tra thông tin đăng nhập
		User u = new User();
		UserObject user = u.Login(username, password);

		if (user.getUser_account_name() != null) {
			// Nếu đăng nhập thành công, lưu thông tin vào session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			if(user.getUser_role() == 0) {
				response.sendRedirect(request.getContextPath() + "/trangchu");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/admin-dashboard");
			}
		} else {
			response.setCharacterEncoding("UTF-8");
			String noti = "Account or password is incorrect!!! Please try again.";
			request.setAttribute("noti", noti);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
