package it602003.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageDisplayServlet
 */
@WebServlet("/ImageDisplayServlet")
public class ImageDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getParameter("imageName"); // Nhận tên ảnh từ request

        // Xác định đường dẫn tới thư mục chứa ảnh trong thư mục tạm thời của Eclipse
        String uploadPath = getServletContext().getRealPath("/") + "image/";
        String imagePath = uploadPath + imageName;

        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            // Set content type của ảnh
            response.setContentType(getServletContext().getMimeType(imagePath));

            // Đọc dữ liệu từ file ảnh và gửi về client
            try (OutputStream out = response.getOutputStream();
                 FileInputStream in = new FileInputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.getWriter().println("Không tìm thấy ảnh");
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
