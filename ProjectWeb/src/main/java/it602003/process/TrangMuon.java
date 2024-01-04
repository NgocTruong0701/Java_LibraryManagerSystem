package it602003.process;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it602003.objects.BookObject;
import it602003.objects.BorrowingFormObject;
import it602003.objects.UserObject;

/**
 * Servlet implementation class TrangMuon
 */
@WebServlet("/trangmuon")
@MultipartConfig
public class TrangMuon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrangMuon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		int book_id = Integer.parseInt(request.getParameter("bid"));
		BookObject book_trang_muon = book.searchBook(book_id);
		request.setAttribute("book_trang_muon", book_trang_muon);

		HttpSession session = request.getSession();
		UserObject user = (UserObject) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/TrangMuon.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("taiKhoan");
		String password = request.getParameter("matKhau");
		int book_id = Integer.parseInt(request.getParameter("bookId"));

		User u = new User();
		BorrowingForm b = new BorrowingForm();
		Book book = new Book();

		BookObject booko = book.searchBook(book_id);

		if (userName == null && password == null) {
			int user_id = Integer.parseInt(request.getParameter("userId"));
			BorrowingFormObject br = new BorrowingFormObject();

			// Lấy thời gian hiện tại
			LocalDateTime currentTime = LocalDateTime.now();
			// Chuyển đổi thành chuỗi để lưu vào cơ sở dữ liệu
			String currentTimeString = currentTime.toString();

			LocalDateTime futureTime = currentTime.plusDays(30);
			String futureTimeString = futureTime.toString();

			br.setBorrowing_form_date(currentTimeString);
			br.setBorrowing_form_deposit(booko.getBook_price());
			br.setBorrowing_form_due_date(futureTimeString);
			br.setUser_id(user_id);
			br.setBook_id(book_id);
			
			boolean result = b.addBorrowingForms(br);
			if(result) {
				System.out.println("Thêm phiếu mượn thành công.");
				book.updateInventoryNumber(book_id, booko.getBook_inventory_number() - 1);
				response.sendRedirect(request.getContextPath() + "/borrowing-form?user_id=" + user_id);
			}
		} else {
			UserObject user = u.Login(userName, password);

			BorrowingFormObject br = new BorrowingFormObject();

			// Lấy thời gian hiện tại
			LocalDateTime currentTime = LocalDateTime.now();
			// Chuyển đổi thành chuỗi để lưu vào cơ sở dữ liệu
			String currentTimeString = currentTime.toString();

			LocalDateTime futureTime = currentTime.plusDays(30);
			String futureTimeString = futureTime.toString();

			br.setBorrowing_form_date(currentTimeString);
			br.setBorrowing_form_deposit(booko.getBook_price());
			br.setBorrowing_form_due_date(futureTimeString);
			br.setUser_id(user.getUser_id());
			br.setBook_id(book_id);
			
			boolean result = b.addBorrowingForms(br);
			if(result) {
				System.out.println("Thêm phiếu mượn thành công.");
				book.updateInventoryNumber(book_id, booko.getBook_inventory_number() - 1);
				response.sendRedirect(request.getContextPath() + "/borrowing-form?user_id=" + user.getUser_id());
			}
		}
	}

}
