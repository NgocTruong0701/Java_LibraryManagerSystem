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
import it602003.objects.BookObject;

/**
 * Servlet implementation class SearchTrangChu
 */
@WebServlet("/searchtrangchu")
public class SearchTrangChu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTrangChu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String searchOption = request.getParameter("select_search");
        String searchKeyword = request.getParameter("search_keyword");
        System.out.println(searchKeyword);
        System.out.println(searchOption);
        // Thực hiện tìm kiếm
        ArrayList<BookObject> searchResults = performSearch(searchOption, searchKeyword);
        System.out.println(searchResults.size());
        // Chuyển kết quả tìm kiếm vào request
        request.setAttribute("sizeSearchResults", searchResults.size());
        request.setAttribute("searchResults", searchResults);

        // Chuyển hướng đến trang kết quả tìm kiếm
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/search_trang_chu.jsp");
        dispatcher.forward(request, response);
	}

	private ArrayList<BookObject> performSearch(String searchOption, String searchKeyword) {
		// TODO Auto-generated method stub
		// TODO: Kết nối với cơ sở dữ liệu và thực hiện tìm kiếm
		Book book = new Book();
		Author author = new Author();
		Publisher publisher = new Publisher();
	    // Danh sách kết quả tìm kiếm
	    ArrayList<BookObject> searchResults = new ArrayList<>();

	    // Tùy thuộc vào lựa chọn tìm kiếm
	    switch (searchOption) {
	        case "1":
	            // Tìm theo tên sách
	            searchResults = book.searchBook(searchKeyword);
	            break;
	        case "2":
	            // Tìm theo tên tác giả
	        	searchResults = book.searchBooksByAuthorName(searchKeyword);
	            break;
	        case "3":
	            // Tìm theo nhà xuất bản
	            searchResults = book.searchBooksByPublisherName(searchKeyword);
	            break;
	        case "4":
	            // Tìm theo nhà xuất bản
	        	searchResults = book.searchBooks(searchKeyword);
	            break;
	        default:
	            // Mặc định tìm kiếm theo tất cả
	            searchResults = book.searchBooks(searchKeyword);
	            break;
	    }

	    return searchResults;
	}

}
