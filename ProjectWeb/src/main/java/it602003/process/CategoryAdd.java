package it602003.process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it602003.objects.*;

/**
 * Servlet implementation class CategoryAdd
 */
@WebServlet("/categoryadd")
public class CategoryAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/AddCategory.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String category_name = request.getParameter("category_name");
		//int category_total_book = Integer.parseInt(request.getParameter("category_total_book"));
		CategoryObject categoryObject = new CategoryObject();
		categoryObject.setCategory_name(category_name);
		//categoryObject.setCategory_total_book(category_total_book);
		Category category = new Category();
		category.addCategory(categoryObject);
		response.sendRedirect(request.getContextPath() + "/categoryview");
	}

}
