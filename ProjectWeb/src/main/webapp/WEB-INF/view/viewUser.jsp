<%@page import="java.io.File"%>
<%@page import="it602003.process.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.objects.UserObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Head content -->
<!-- Import Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="/lnt.library/css/main.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<link href="/lnt.library/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link href="/lnt.library/css/apexcharts.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<!-- Import header -->
	<div class="custom-container">
		<jsp:include page="component/header.jsp" />
		<div class="row">
			<!-- Import sidebar -->
			<aside class="col-lg-2">
				<jsp:include page="component/sidebar.jsp" />
			</aside>

			<!-- Main content -->
			<main class="col-lg-10 container-lg">
				<div class="card my-4">
					<div class="card-header text-bg-primary"></div>
					<div class="card-body">
						<h1 class="mb-4">Danh sách người mượn</h1>
						<a href="${pageContext.request.contextPath}/user/create" class="btn btn-primary mb-3">Thêm mới</a>
						<table class="table table-striped table-hover table-sm">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">User Name</th>
									<th scope="col">User Image</th>
									<th scope="col">User Phone Number</th>
									<th scope="col">User Address</th>
									<th scope="col">User Account Name</th>
									<th scope="col">User Account Password</th>
									<th scope="col">User Role</th>
									<th scope="col" class="align-middle d-flex justify-content-center" colspan="2">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								int index = 0;
								User u = new User();
								ArrayList<UserObject> users = u.getuserObjects(0, (byte) 10);
								// Assume users is a list of User objects retrieved from your backend
								for (UserObject user : users) {
								%>
								<tr>
									<th scope="row" class="align-middle"><%=index%></th>
									<td class="align-middle"><%=user.getUser_name()%></td>
									<td class="align-middle"><img
										src="ImageDisplayServlet?imageName=<%=user.getUser_image()%>"
										alt="User Image" width="50" height="50"></td>
									<td class="align-middle"><%=user.getUser_phone_number()%></td>
									<td class="align-middle"><%=user.getUser_address()%></td>
									<td class="align-middle"><%=user.getUser_account_name()%></td>
									<td class="align-middle"><%=user.getUser_account_password()%></td>
									<td class="align-middle"><%=user.getUser_role() == 0 ? "User" : "Admin"%></td>
									<td><a href="#" class="btn btn-outline-warning btn-sm"><i
											class="fa-solid fa-pen-to-square"></i> Edit</a></td>
									<td><a href="#" data-bs-toggle="modal"
										data-bs-target="#del<%=user.getUser_id()%>"
										class="btn btn-outline-danger btn-sm"><i
											class="fa-regular fa-trash-can"></i> Delete</a></td>
								</tr>
								<div class="modal fade" id="del<%=user.getUser_id()%>"
									data-bs-backdrop="static" data-bs-keyboard="false"
									tabindex="-1" aria-labelledby="delLabel<%=user.getUser_id()%>"
									aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<div class="modal-header text-bg-danger bg-gradient">
												<h1 class="modal-title fs-5"
													id="delLabel<%=user.getUser_id()%>">
													<i class="fa-solid fa-triangle-exclamation"></i> Xóa người
													dùng
												</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body text-danger text-center">
												<h5>
													Bạn có thực sự muốn xóa
													<%=user.getUser_id()%></h5>
											</div>
											<div class="modal-footer text-bg-light bg-gradient">
												<form
													action="${pageContext.request.contextPath}/user/delete"
													method="post" id="deleteForm">
													<input type="hidden" id="deleteItemId" name="id"
														value="<%=user.getUser_id()%>">
													<button type="submit" class="btn btn-danger">
														<i class="fa-solid fa-trash"></i> Xóa
													</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">
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
					</div>
					<div class="card-footer text-bg-info"></div>
				</div>
			</main>
		</div>
	</div>

	<!-- Import footer -->
	<jsp:include page="component/footer.jsp" />

	<!-- Import Bootstrap 5 JavaScript -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
