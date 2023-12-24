package it602003.process;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookRemove
 */
@WebServlet("/bookremove")
public class BookRemoveControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRemoveControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String book_id_remove = request.getParameter("bid");
		int intValue = Integer.parseInt(book_id_remove);
		Book book = new Book();
		String ketQua;
		if(book.removeBook(intValue)) {
			ketQua = "Đã xóa thành công sách có ID là "+book_id_remove;
		}else {
			ketQua = "Xóa không thành công sách có ID là "+book_id_remove;
		}
		System.out.println(ketQua);
		response.sendRedirect("bookview");
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
