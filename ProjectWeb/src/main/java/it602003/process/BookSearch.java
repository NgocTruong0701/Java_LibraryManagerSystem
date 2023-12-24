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

import it602003.objects.BookObject;

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/booksearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearch() {
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
		Book book = new Book();
		HttpSession session = request.getSession();
		switch (luaChon) {
		case 1: {
			ArrayList<BookObject> booksearch = book.searchBook(name);
			System.out.println(booksearch.size());
			session.setAttribute("booksearch", booksearch);
			session.setAttribute("luaChon", luaChon);
			session.setAttribute("thongTinSearch", name);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("bookview");
		    dispatcher.forward(request, response);

		}
		case 2: {
			ArrayList<BookObject> booksearch = book.searchBook(name);
			System.out.println(booksearch.size());
			
			request.setAttribute("numberBook", booksearch.size());
			request.setAttribute("booksearch", booksearch);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("bookview");
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
