package it602003.process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.PublisherObject;

/**
 * Servlet implementation class PublisherEdit
 */
@WebServlet("/publisheredit")
public class PublisherEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublisherEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int publisher_id = Integer.parseInt(request.getParameter("pid"));
		Publisher publisher = new Publisher();
		PublisherObject publisherObject  = publisher.searchPublisher(publisher_id);
		request.setAttribute("publisherObject", publisherObject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/EditPublisher.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int publisher_id = Integer.parseInt(request.getParameter("publisher_id"));
		String publisher_name = request.getParameter("publisher_name");
		String publisher_phone_numer = request.getParameter("publisher_phone_number");
		String publisher_address = request.getParameter("publisher_address");
		PublisherObject publisherObject = new PublisherObject();
		publisherObject.setPublisher_id(publisher_id);
		publisherObject.setPublisher_name(publisher_name);
		publisherObject.setPublisher_phone_number(publisher_phone_numer);
		publisherObject.setPublisher_address(publisher_address);
		Publisher publisher = new Publisher();
		
		try {
			publisher.editPublisher(publisherObject);
		} catch (Exception e) {
		}
		response.sendRedirect(request.getContextPath()+"/publisherview");
	}

}
