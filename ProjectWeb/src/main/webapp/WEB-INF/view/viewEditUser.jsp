<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>chỉnh sửa thông tin người dùng</title>
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
				<div class="form-container">
					<h2>Chỉnh sửa thông tin người dùng</h2>
					<hr>
					<form enctype="multipart/form-data"
						action="${pageContext.request.contextPath}/user/update"
						accept-charset="UTF-8" method="post">
						<!-- Thêm input hidden để giữ ID người dùng -->
						<div class="form-group mb-2">
							<input type="hidden" id="user_id" name="user_id"
								value="<%=request.getAttribute("user_id")%>" />
						</div>

						<div class="form-group mb-2">
							<label for="user_name">User Name:</label> <input type="text"
								class="form-control" id="user_name" name="user_name"
								value="<%=request.getAttribute("user_name")%>"
								required="required" />
						</div>

						<!-- Chọn hình ảnh -->
						<div class="form-group mb-2">
							<label for="user_image">User Image:</label> <img
								id="previewImage"
								src="${pageContext.request.contextPath}/ImageDisplayServlet?imageName=<%=request.getAttribute("user_image")%>"
								alt="User Image" width="150" height="150" class="border border-info"> <input
								type="file" class="form-control-file" id="user_image_input"
								name="user_image"
								value="<%=request.getAttribute("user_image")%>"
								onchange="previewSelectedImage(this)"> <input
								type="hidden" id="user_image_old" name="user_image_old"
								value="<%=request.getAttribute("user_image")%>" />
						</div>

						<!-- Số điện thoại -->
						<div class="form-group mb-2">
							<label for="user_phone_number">Phone Number:</label> <input
								type="text" class="form-control" id="user_phone_number"
								value="<%=request.getAttribute("user_phone_number")%>"
								name="user_phone_number" required="required">
						</div>

						<!-- Địa chỉ -->
						<div class="form-group mb-2">
							<label for="user_address">Address:</label> <input type="text"
								class="form-control" id="user_address" name="user_address"
								value="<%=request.getAttribute("user_address")%>"
								required="required">
						</div>

						<!-- Tên tài khoản -->
						<div class="form-group mb-2">
							<label for="user_account_name">Account Name:</label> <input
								type="text" class="form-control" id="user_account_name"
								name="user_account_name" required="required"
								value="<%=request.getAttribute("user_account_name")%>" readonly>
						</div>

						<!-- Mật khẩu tài khoản -->
						<div class="form-group mb-3">
							<label for="user_account_password">Account Password:</label>
							<div class="input-group align-items-center">
								<input type="password" class="form-control"
									id="user_account_password" name="user_account_password"
									required="required"
									value="<%=request.getAttribute("user_password")%>">
								<div class="input-group-append ms-1">
									<span class="input-group-text"
										onclick="togglePasswordVisibility()"> <i
										id="togglePassword" class="fas fa-eye"></i>
									</span>
								</div>
							</div>
						</div>

						<div>
							<!-- Nút Submit -->
							<button type="submit" class="btn btn-primary">Cập nhật</button>
							<a href="${pageContext.request.contextPath}/user"
								class="btn btn-primary ml-6">Trở về</a>
						</div>
					</form>
				</div>
			</main>
		</div>
	</div>

	<!-- Import footer -->
	<jsp:include page="component/footer.jsp" />

	<!-- Import Bootstrap JavaScript -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
		function previewSelectedImage(input) {
			if (input.files && input.files[0]) {
				const reader = new FileReader();

				reader.onload = function(e) {
					document.getElementById('previewImage').setAttribute('src',
							e.target.result);
				};

				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>

	<script>
		function togglePasswordVisibility() {
			const passwordField = document
					.getElementById('user_account_password');
			const toggleIcon = document.getElementById('togglePassword');

			if (passwordField.type === 'password') {
				passwordField.type = 'text';
				toggleIcon.classList.remove('fa-eye');
				toggleIcon.classList.add('fa-eye-slash');
			} else {
				passwordField.type = 'password';
				toggleIcon.classList.remove('fa-eye-slash');
				toggleIcon.classList.add('fa-eye');
			}
		}
	</script>
</body>
</html>