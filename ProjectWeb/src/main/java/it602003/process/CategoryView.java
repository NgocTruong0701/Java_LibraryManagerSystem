package it602003.process;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.CategoryObject;

/**
 * Servlet implementation class CategoryView
 */
@WebServlet("/categoryview")
public class CategoryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            Category category = new Category();
            ArrayList<CategoryObject> categorys = category.getCategoryObjects(null, (byte) 100);
            request.setAttribute("categorys", categorys);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/CategoryView.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Ghi log cho ngoại lệ hoặc hiển thị một trang lỗi
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi");
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
