package it602003.process;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.AuthorObject;
import it602003.objects.CategoryObject;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/admin-dashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Book b = new Book();
		int totalBooks = b.getTotalBooks();
		request.setAttribute("totalBooks", totalBooks);
		
		User u = new User();
		int totalUsers = u.getTotalUsers(0);
		request.setAttribute("totalUsers", totalBooks);
		
		Author a = new Author();
		ArrayList<AuthorObject> authors = a.getAllAuthors();
		request.setAttribute("authors", authors);
		
		Category c = new Category();
		ArrayList<CategoryObject> categories = c.getAllCategories();
		request.setAttribute("categories", categories);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Index.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
