package it602003.process;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.UserObject;

/**
 * Servlet implementation class UserView
 */
@WebServlet("/user")
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
		response.setCharacterEncoding("UTF-8");
		// Lấy trang hiện tại từ request (nếu không có thì mặc định là trang 1)
	    int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
	    // Số lượng item mỗi trang và role
	    int pageSize = 5;
	    int role = 0;
	    
	    User u = new User();
	    ArrayList<UserObject> users = u.getuserObjects(role, currentPage, pageSize);
	    
	    int totalUsers = u.getTotalUsers(role);
	    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
	    
	    request.setAttribute("users", users);
	    request.setAttribute("totalPages", totalPages);
	    request.setAttribute("currentPage", currentPage);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/viewUser.jsp");
	    dispatcher.forward(request, response);
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
