package it602003.process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.CategoryObject;

/**
 * Servlet implementation class CategoryEdit
 */
@WebServlet("/categoryedit")
public class CategoryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int category_id = Integer.parseInt(request.getParameter("cid"));
		Category category = new Category();
		CategoryObject categoryObject  = category.searchCategory(category_id);
		request.setAttribute("categoryObject", categoryObject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/EditCategory.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String category_name = request.getParameter("category_name");
		//int category_total_book = Integer.parseInt(request.getParameter("category_total_book"));
		CategoryObject categoryObject = new CategoryObject();
		categoryObject.setCategory_name(category_name);
		categoryObject.setCategory_id(category_id);
		//categoryObject.setCategory_total_book(category_total_book);
		Category category = new Category();
		
		try {
			category.editCategory(categoryObject);
		} catch (Exception e) {
		}
		response.sendRedirect(request.getContextPath()+"/categoryview");
	}

}
