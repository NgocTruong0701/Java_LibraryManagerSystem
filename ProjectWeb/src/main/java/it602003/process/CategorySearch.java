package it602003.process;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it602003.objects.CategoryObject;

/**
 * Servlet implementation class CategorySearch
 */
@WebServlet("/categorysearch")
public class CategorySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorySearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int luaChon = Integer.parseInt(request.getParameter("luaChonSearch"));
		System.out.println(luaChon);
		String name = request.getParameter("thongTinSearch");
		System.out.println(name);
		Category category = new Category();
		HttpSession session = request.getSession();
		switch (luaChon) {
		case 1: {
			ArrayList<CategoryObject> categorysearch = category.searchCategory(name);
			System.out.println(categorysearch.size());
			session.setAttribute("categorysearch", categorysearch);
			session.setAttribute("luaChon", luaChon);
			session.setAttribute("thongTinSearch", name);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("categoryview");
		    dispatcher.forward(request, response);

		}
		case 2: {
			ArrayList<CategoryObject> categorysearch = category.searchCategory(name);
			System.out.println(categorysearch.size());
			session.setAttribute("categorysearch", categorysearch);
			session.setAttribute("luaChon", luaChon);
			session.setAttribute("thongTinSearch", name);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("categoryview");
		    dispatcher.forward(request, response);

		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + name);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
