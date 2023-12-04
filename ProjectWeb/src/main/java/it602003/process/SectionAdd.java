package it602003.process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import it602003.objects.SectionObject;

/**
 * Servlet implementation class SectionAdd
 */
@WebServlet("/section/addsection")
public class SectionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Chuyển hướng (forward) yêu cầu tới trang JSP
		// "/WEB-INF/addSection.jsp" là đường dẫn đến trang JSP mà bạn muốn nhúng.
		// request.getRequestDispatcher() sử dụng để lấy một đối tượng RequestDispatcher cho trang JSP.
		// dispatcher.forward(request, response) chuyển hướng yêu cầu từ Servlet tới trang JSP để hiển thị trên trình duyệt.
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addSection.jsp");
	    dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Lấy dữ liệu từ trang JSP
	    String sectionName = request.getParameter("sectionName");
	    String sectionNotes = request.getParameter("sectionNotes");
	    
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    
	    Section s = new Section();
	    
	    SectionObject nsec = new SectionObject();
	    nsec.setSection_name(sectionName);
	    nsec.setSection_notes(sectionNotes); 
	    nsec.setSection_created_date(currentDateTime.toString()); // Đặt giá trị thời gian hiện tại cho section_created_date
	    
	    // Add
	    s.addSection(nsec);
	    
	    // Chuyển hướng trang
	    response.sendRedirect(request.getContextPath() + "/section/view");
	}

}
