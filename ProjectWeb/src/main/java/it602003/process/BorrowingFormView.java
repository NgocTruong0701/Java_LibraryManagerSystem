package it602003.process;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it602003.objects.BookObject;
import it602003.objects.BorrowingFormObject;
import it602003.objects.ReturnSlipObject;
import it602003.objects.UserObject;

/**
 * Servlet implementation class UserView
 */
@WebServlet("/borrowing-form")
public class BorrowingFormView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowingFormView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserObject user = (UserObject) session.getAttribute("user");
		request.setAttribute("user", user);
		
		// Lấy trang hiện tại từ request (nếu không có thì mặc định là trang 1)
		int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int currentPage2 = Integer.parseInt(request.getParameter("page2") != null ? request.getParameter("page2") : "1");
		// Số lượng item mỗi trang và role
		int pageSize = 5;

		BorrowingForm b = new BorrowingForm();
		ReturnSlip r = new ReturnSlip();
		ArrayList<BorrowingFormObject> brs;
		ArrayList<ReturnSlipObject> rls;
		if(user != null) {
			if(user.getUser_role() == 1) {
				brs = b.getBorrowingForms(currentPage, pageSize);
				rls = r.getReturnSlips(currentPage2, pageSize);
			}
			else {
				brs = b.getBorrowingFormsByUserId(user.getUser_id());
				rls = r.getReturnSlipsByUserId(user.getUser_id());
			}
			
			int totalBorrowingForms = b.getTotalBorrowingForms();
			int totalPages = (int) Math.ceil((double) totalBorrowingForms / pageSize);
			
			int totalReturnSlips = b.getTotalBorrowingForms();
			int totalPagesReturnSlip = (int) Math.ceil((double) totalReturnSlips / pageSize);
			

			request.setAttribute("brs", brs);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("currentPage", currentPage);
			
			request.setAttribute("rls", rls);
			request.setAttribute("totalPages2", totalPagesReturnSlip);
			request.setAttribute("currentPage2", currentPage2);
			

			if(user.getUser_role() == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/BorrowingFormsUserView.jsp");
				dispatcher.forward(request, response);
			}
			if(user.getUser_role() == 1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/FormAdminView.jsp");
				dispatcher.forward(request, response);
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserObject user = (UserObject) session.getAttribute("user");
		request.setAttribute("user", user);
		request.setCharacterEncoding("UTF-8");
		try {
			int brId = Integer.parseInt(request.getParameter("id"));

			BorrowingForm b = new BorrowingForm();
			BorrowingFormObject br = b.getBorrowingFormsById(brId);
			
			Book book = new Book();
			BookObject bo = book.searchBook(br.getBook_id());
			
			ReturnSlip r = new ReturnSlip();
			
			if (b.removeBorrowingFormsById(brId)) {
				book.updateInventoryNumber(bo.getBook_id(), bo.getBook_inventory_number() + 1);
				
				ReturnSlipObject rlo = new ReturnSlipObject();
				
				LocalDateTime currentTime = LocalDateTime.now();
				String currentTimeString = currentTime.toString();
				
				rlo.setReturn_slip_date(currentTimeString);
				rlo.setReturn_slip_refund(br.getBorrowing_form_deposit());
				String timeDeadlineString = br.getBorrowing_form_due_date()+"T12:30:00";
				LocalDateTime timeDeadline = LocalDateTime.parse(timeDeadlineString);
				// Tính toán khoảng thời gian giữa hai thời điểm
		        Duration duration = Duration.between(currentTime, timeDeadline);
		        long daysDifference = duration.toDays();
		        
		        double feeLate = 0;
		        System.out.println("Date late: " + daysDifference);
		        if(daysDifference < 0) {
		        	feeLate  += Math.abs(daysDifference) * 1000;
		        }
				rlo.setReturn_slip_late_fee(feeLate);
				rlo.setUser_id(br.getUser_id());
				rlo.setBorrowing_form_id(brId);
				
				if(r.addBorrowingForms(rlo)) {
					System.out.print("Thêm return slip thành công");
					response.sendRedirect(request.getContextPath() + "/borrowing-form?user_id=" + user.getUser_id());
				}
			} else {
				response.getWriter()
						.println("<script>alert('Lỗi: Không thể xóa người dùng, vui lòng thử lại');</script>");
			}

		} catch (Exception e) {
			e.printStackTrace(); // In thông tin lỗi ra console
			response.getWriter().println("<script>alert('Lỗi: " + e.getMessage() + "');</script>");
		}
	}

}
