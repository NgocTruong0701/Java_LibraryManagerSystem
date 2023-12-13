<%@page import="java.util.ArrayList"%>
<%@page import="it602003.objects.UserObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Head content -->
<!-- Import Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="/lnt.library/css/main.css" />
</head>
<body>
	<!-- Import header -->
	<div class="custom-container">
		<jsp:include page="component/header.jsp" />
		<div class="row">
			<!-- Import sidebar -->
			<aside class="col-lg-3">
				<jsp:include page="component/sidebar.jsp" />
			</aside>

			<!-- Main content -->
			<main class="col-lg-9">
				<h1 class="mb-4">User List</h1>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Index</th>
                                <th>User Name</th>
                                <th>User Image</th>
                                <th>User Phone Number</th>
                                <th>User Address</th>
                                <th>User Account Name</th>
                                <th>User Account Password</th>
                                <th>User Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                            ArrayList<UserObject> users = new ArrayList<>();
                            UserObject user1 = new UserObject(1, "Lê Ngọc Trường", "/lnt.library/image/avatar.jpg", "01234567",
                                    "80/119, Xuân Phương, Nam Từ Liêm, Hà Nội", "ngoctruongfa", "1234567", 1);
                            users.add(user1);
                            // Assume users is a list of User objects retrieved from your backend
                            for (UserObject user : users) {
                            %>
                            <tr>
                                <td><%=user.getUser_id()%></td>
                                <td><%=user.getUser_name()%></td>
                                <td><img src="<%=user.getUser_image()%>" alt="User Image"
                                        width="50" height="50"></td>
                                <td><%=user.getUser_phone_number()%></td>
                                <td><%=user.getUser_address()%></td>
                                <td><%=user.getUser_account_name()%></td>
                                <td><%=user.getUser_account_password()%></td>
                                <td><%=user.getUser_role() == 1 ? "User" : "Admin"%></td>
                            </tr>
                            <% 
                            }
                            %>
                        </tbody>
                    </table>
			</main>
		</div>
	</div>

	<!-- Import footer -->
	<jsp:include page="component/footer.jsp" />

	<!-- Import Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>