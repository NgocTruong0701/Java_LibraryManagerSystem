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
import it602003.objects.CategoryObject;
import it602003.objects.UserObject;

/**
 * Servlet implementation class TrangChu
 */
@WebServlet("/trangchu")
public class TrangChu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrangChu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		Category category_trang_chu = new Category();
//		Book book_trang_chu = new Book();
//		ArrayList<BookObject> list_book_content2 = null;
//		ArrayList<CategoryObject> categorys_trang_chu =  category_trang_chu.getAllCategories();
//		if(request.getParameter("cid")!=null) {
//			int cid = Integer.parseInt(request.getParameter("cid"));
//			list_book_content2 = book_trang_chu.getBooksByCategoryWithPagination(cid, 0, 8);
//		}else {
//			int cid = 1;
//			list_book_content2 = book_trang_chu.getBooksByCategoryWithPagination(cid, 0, 8);
//		}
//		
//		request.setAttribute("list_book_content2", list_book_content2);
//		request.setAttribute("categorys_trang_chu", categorys_trang_chu);
//	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/TrangChu.jsp");
//	    dispatcher.forward(request, response);
		try {
			Category category_trang_chu = new Category();// tạo đối tượng category
	        Book book_trang_chu = new Book();// tạo đối tượng book
	        ArrayList<BookObject> list_book_content2 = null; //bảng con_tent2
	        ArrayList<CategoryObject> categorys_trang_chu = category_trang_chu.getAllCategories(); // lấy tất cả danh mục
	        ArrayList<BookObject> books_new = null;
	        books_new = book_trang_chu.getNewestBooks(6);
	        int pageSize = 9; // Số sách trên mỗi trang
	        int page = 1; // Trang mặc định
	        int cid;
	        int totalBooks;
	        if (request.getParameter("cid") != null) {
	            cid = Integer.parseInt(request.getParameter("cid"));
	            totalBooks = book_trang_chu.getBooksByCategory(cid, (byte)100).size();
	            if (request.getParameter("page") != null) {
	                page = Integer.parseInt(request.getParameter("page"));
	            }
	            int start = (page - 1) * pageSize;
	            list_book_content2 = book_trang_chu.getBooksByCategoryWithPagination(cid, start, pageSize);
	            //name_category = category_trang_chu.searchCategory(cid).getCategory_name();
	        } else {
	            // Nếu không có cid, sẽ lấy sách theo category đầu tiên
	            cid = 1;
	            
	            if (request.getParameter("page") != null) {
	                page = Integer.parseInt(request.getParameter("page"));
	            }
	            int start = (page - 1) * pageSize;
	            list_book_content2 = book_trang_chu.getBooksByCategoryWithPagination(cid, start, pageSize);
	            totalBooks = book_trang_chu.getBooksByCategory(cid, (byte)100).size();
	            //name_category = category_trang_chu.searchCategory(cid).getCategory_name();
	        }
	         // Lấy tổng số sách dựa trên danh mục
	        
	        int totalPages = (int) Math.ceil((double) totalBooks / (double) pageSize); // Tính tổng số trang
	        
	        request.setAttribute("totalPages", totalPages);
	        request.setAttribute("books_new", books_new);
	        request.setAttribute("list_book_content2", list_book_content2);
	        request.setAttribute("categorys_trang_chu", categorys_trang_chu);
	        request.setAttribute("currentPage", page);
	        
	        HttpSession session = request.getSession();
	        UserObject user = (UserObject) session.getAttribute("user");
	        request.setAttribute("user", user);
	 
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/TrangChu.jsp");
	        dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/TrangChu.jsp");
	        dispatcher.forward(request, response);
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
