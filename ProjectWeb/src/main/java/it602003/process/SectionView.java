package it602003.process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it602003.objects.SectionObject;

/**
 * Servlet implementation class SectionView
 */
@WebServlet("/section/view")
public class SectionView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionView() {
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
		response.setContentType(CONTENT_TYPE);
		// Get context path của ứng dụng
		String contextPath = request.getContextPath();

		// Get writer to write html
		PrintWriter out = response.getWriter();
		out.append("<!doctype html>");
		out.append("<html lang=\"en\">");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.append("<title>Section View</title>");
		out.append(
				"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
		out.append("<link href=\"/lnt.library/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
		out.append("<link href=\"/lnt.library/css/apexcharts.css\" rel=\"stylesheet\" type=\"text/css\" />");
		out.append("</head>");

		out.append("<body>");

		out.append("<div class=\"container-lg\">");
		out.append("<div class=\"card my-4\">");
		out.append("<div class=\"card-header text-bg-primary\"></div>");
		out.append("<div class=\"card-body\">");
		out.append("<div>");
		out.append("<h1>Danh sách Chuyên mục</h1>");
		out.append("<a href=\"" + contextPath + "/section/addsection\" class=\"btn btn-primary\">Thêm mới</a>");
		out.append("</div>");

		out.append("<table class=\"table table-striped table-hover table-sm \">");
		out.append("<thead>");
		out.append("<tr>");
		out.append("<th scope=\"col\">#</th>");
		out.append("<th scope=\"col\">Ngày tạo</th>");
		out.append("<th scope=\"col\">Tên chuyên mục</th>");
		out.append("<th scope=\"col\">Ghi chú</th>");
		out.append("<th scope=\"col\" colspan=\"2\" >Thực hiện</th>");
		out.append("</tr>");
		out.append("</thead>");
		out.append("<tbody>");

		Section s = new Section();
		ArrayList<SectionObject> list = s.getSectionObjects(null, (byte)100);
		list.forEach(item -> {
			out.append("<tr>");
			out.append("<th scope=\"row\">" + item.getSection_id() + "</th>");
			out.append("<td class=\"align-middle\">" + item.getSection_created_date() + "</td>");
			out.append("<td class=\"align-middle\">" + item.getSection_name() + "</td>");
			out.append("<td class=\"align-middle\">" + item.getSection_notes() + "</td>");
			out.append(
					"<td><a href=\"#\" class=\"btn btn-outline-warning btn-sm\" ><i class=\"fa-solid fa-pen-to-square\"></i> Sửa</a></td>");
			out.append("<td><a href=\"#\" data-bs-toggle=\"modal\" data-bs-target=\"#del" + item.getSection_id()
					+ "\" class=\"btn btn-outline-danger btn-sm\" ><i class=\"fa-regular fa-trash-can\"></i> Xóa</a></td>");
			out.append("</tr>");

			out.append(this.confirmDel(item));
		});

		out.append("</tbody>");
		out.append("</table>");
		out.append("</div>");
		out.append("<div class=\"card-footer text-bg-info\"></div>");
		out.append("</div>");
		
		// Hiển thị biểu đồ thống kê
		out.append(this.viewChart(list));
		
		
		// Add JS
		out.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>");
		out.append("<script src=\"/lnt.library/js/apexcharts.min.js\" language=\"javascript\"></script>");
	}

	private StringBuilder viewChart(ArrayList<SectionObject> list) {
		// TODO Auto-generated method stub
		StringBuilder ids = new StringBuilder();
		StringBuilder names = new StringBuilder();
		
		list.forEach(item->{
			ids.append(item.getSection_id());
			names.append("'"+item.getSection_name()+"'");
			if(list.indexOf(item)<list.size()-1) {
				ids.append(",");
				names.append(",");
			}
		});
		
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<h5 class=\"card-title\">Biểu đồ Chuyên mục</h5>");
		tmp.append("<div id=\"barChart\"></div>");
		tmp.append("<script>");
		tmp.append("document.addEventListener(\"DOMContentLoaded\", () => {");
		tmp.append("new ApexCharts(document.querySelector(\"#barChart\"), {");
		tmp.append("series: [{");
		tmp.append("name: 'Danh sách Chuyên mục',");
		tmp.append("data: ["+ids+"]");
		tmp.append("}],");
		tmp.append("chart: {type: 'bar', height: 350, fontFamily: 'Tahoma, sans-serif'},");
		tmp.append("plotOptions: {bar: {borderRadius: 4, horizontal: true,}},");
		tmp.append("dataLabels: {enabled: false},");
		tmp.append("");
		tmp.append("xaxis: {");
		tmp.append("categories: ["+names+"],");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '18px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 600,");
		tmp.append("cssClass: 'apexcharts-xaxis-label',");
		tmp.append("},");
		tmp.append("}");
		tmp.append("},");
		tmp.append("");
		tmp.append("yaxis: {");
		tmp.append("show: true,");
		tmp.append("labels: {");
		tmp.append("show: true,");
		tmp.append("align: 'right',");
		tmp.append("minWidth: 250,");
		tmp.append("maxWidth: 400,");
		tmp.append("style: {");
		tmp.append("colors: [],");
		tmp.append("fontSize: '15px',");
		tmp.append("fontFamily: 'Helvetica, Arial, sans-serif',");
		tmp.append("fontWeight: 400,");
		tmp.append("cssClass: 'apexcharts-yaxis-label',");
		tmp.append("},");
		tmp.append("},");
		tmp.append("}");
		tmp.append("}).render();");
		tmp.append("});");
		tmp.append("</script>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}

	private StringBuilder confirmDel(SectionObject item) {
		// TODO Auto-generated method stub
		StringBuilder out = new StringBuilder();

		out.append("<div class=\"modal fade\" id=\"del" + item.getSection_id()
				+ "\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"delLabel"
				+ item.getSection_id() + "\" aria-hidden=\"true\">");
		out.append("<div class=\"modal-dialog modal-lg\">");
		out.append("<div class=\"modal-content\">");
		out.append("<div class=\"modal-header text-bg-danger bg-gradient\">");
		out.append("<h1 class=\"modal-title fs-5\" id=\"delLabel" + item.getSection_id()
				+ "\"><i class=\"fa-solid fa-triangle-exclamation\"></i> Xóa chuyên mục</h1>");
		out.append(
				"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		out.append("</div>");
		out.append("<div class=\"modal-body text-danger text-center\">");
		out.append("<h5>Bạn có thực sự muốn xóa " + item.getSection_name() + "</h5>");
		out.append("</div>");
		out.append("<div class=\"modal-footer text-bg-light bg-gradient\">");
		out.append("<button type=\"button\" class=\"btn btn-danger\"><i class=\"fa-solid fa-trash\"></i> Xóa</button>");
		out.append(
				"<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\"><i class=\"fa-solid fa-x\"></i> Hủy</button>");

		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		out.append("</div>");
		return out;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
