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

import it602003.objects.PublisherObject;

/**
 * Servlet implementation class PublisherSearch
 */
@WebServlet("/publishersearch")
public class PublisherSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublisherSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int luaChon = Integer.parseInt(request.getParameter("luaChonSearch"));
				System.out.println(luaChon);
				String name = request.getParameter("thongTinSearch");
				System.out.println(name);
				Publisher publisher = new Publisher();
				HttpSession session = request.getSession();
				switch (luaChon) {
				case 1: {
					ArrayList<PublisherObject> publishersearch = publisher.searchPublisher(name);
					System.out.println(publishersearch.size());
					session.setAttribute("publishersearch", publishersearch);
					session.setAttribute("luaChon", luaChon);
					session.setAttribute("thongTinSearch", name);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("publisherview");
				    dispatcher.forward(request, response);

				}
				case 2: {
					ArrayList<PublisherObject> publishersearch = publisher.searchPublisher(name);
					System.out.println(publishersearch.size());
					session.setAttribute("publishersearch", publishersearch);
					session.setAttribute("luaChon", luaChon);
					session.setAttribute("thongTinSearch", name);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("publisherview");
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
