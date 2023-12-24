<%@page import="com.mysql.cj.x.protobuf.MysqlxDatatypes.Array"%>
<%@page import="java.io.File"%>
<%@page import="it602003.process.Author"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.objects.AuthorObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">

				<div class="card my-4">
					<div class="card-header text-bg-primary"></div>
					<div class="card-body">
						<h1 class="mb-4">Danh sách tác giả</h1>
						<a href="${pageContext.request.contextPath}/author/create" class="btn btn-primary mb-3 text-white">Thêm mới</a>
						<table class="table table-striped table-hover table-sm">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Tên tác giả</th>
									<th scope="col">Ngày sinh</th>
									<th scope="col">Mô tả</th>
									<th scope="col">Avatar</th>
									<th scope="col" class="align-middle justify-content-center" colspan="2">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								int index = 0;
								ArrayList<AuthorObject> authors = (ArrayList<AuthorObject>) request.getAttribute("authors");
								int currentPage = (int) request.getAttribute("currentPage");
								int totalPages = (int) request.getAttribute("totalPages");
								// Assume users is a list of User objects retrieved from your backend
								for (AuthorObject author : authors) {
								%>
								<tr>
									<th scope="row" class="align-middle"><%=index%></th>
									<td class="align-middle"><%=author.getAuthor_name()%></td>
									<td class="align-middle"><%=author.getAuthor_date_of_birth()%></td>
									<td class="align-middle"><%=author.getAuthor_description()%></td>
									<td class="align-middle"><img src="ImageDisplayServlet?imageName=<%=author.getAuthor_image()%>" alt="User Image" width="50" height="50"></td>
									<td class="align-middle"><a href="${pageContext.request.contextPath}/author/update?id=<%= author.getAuthor_id()%>" class="btn btn-outline-warning btn-sm"><i class="fa-solid fa-pen-to-square"></i> Edit</a></td>
									<td class="align-middle"><a href="#" data-bs-toggle="modal" data-bs-target="#del<%=author.getAuthor_id()%>" class="btn btn-outline-danger btn-sm"><i class="fa-regular fa-trash-can"></i> Delete</a></td>
								</tr>
								<div class="modal fade" id="del<%=author.getAuthor_id()%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="delLabel<%=author.getAuthor_id()%>" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header text-bg-danger bg-gradient">
												<h1 class="modal-title fs-5" id="delLabel<%=author.getAuthor_id()%>">
													<i class="fa-solid fa-triangle-exclamation"></i> Xóa tác giả
												</h1>
												<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body text-danger text-center">
												<h5>
													Bạn có thực sự muốn xóa tác giả
													<%=author.getAuthor_name()%></h5>
											</div>
											<div class="modal-footer text-bg-light bg-gradient">
												<form action="${pageContext.request.contextPath}/author/delete" method="post" id="deleteForm">
													<input type="hidden" id="deleteItemId" name="id" value="<%=author.getAuthor_id()%>">
													<button type="submit" class="btn btn-danger">
														<i class="fa-solid fa-trash"></i> Xóa
													</button>
													<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
														<i class="fa-solid fa-x"></i> Hủy
													</button>
												</form>
											</div>
										</div>
									</div>
								</div>
								<%
								index++;
								}
								%>
							</tbody>
						</table>
						<div class="pagination align-items-center">
							<%-- Hiển thị các nút phân trang --%>
							<%
							for (int i = 1; i <= totalPages; i++) {
							%>
							<a href="${pageContext.request.contextPath}/author?page=<%= i %>" class="text-white btn btn-primary me-2 <%= (currentPage == i) ? "active" : "" %>">
								<%=i%>
							</a>
							<%
							}
							%>
							<b class="m-0"> Page: <%=currentPage%>/<%=totalPages%>
							</b>
						</div>
					</div>
					<div class="card-footer text-bg-info"></div>
				</div>

			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>
</body>
</html>
