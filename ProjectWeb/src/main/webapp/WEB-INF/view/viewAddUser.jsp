<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<!-- Head content -->
		<!-- Import Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<link rel="stylesheet" href="/lnt.library/css/main.css" />
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
		<link href="/lnt.library/css/all.min.css" rel="stylesheet" type="text/css" />
		<link href="/lnt.library/css/apexcharts.css" rel="stylesheet" type="text/css" />
	</head>

	<body class="app">
		<!-- Import header -->
		<jsp:include page="component/header.jsp" />
		<div class="app-wrapper">
			<div class="app-content pt-3 p-md-3 p-lg-4">
				<!-- Main content -->
				<main class="container-fluid">
					<div class="form-container">
						<h2>Thêm Người Dùng</h2>
						<hr>
						<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/create"
							accept-charset="UTF-8" method="post">
							<!-- Tên người dùng -->
							<div class="form-group mb-2">
								<label for="user_name">User Name:<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="user_name" name="user_name" required="required" />
							</div>

							<!-- Chọn hình ảnh -->
							<div class="form-group mb-2">
								<label for="user_image">User Image:<span
									class="rq">*</span></label> <img id="previewImage"
									src="/lnt.library/image/default-avatar.png" alt="User Image" width="150"
									height="150" class="border border-info"> <input type="file"
									class="form-control-file" id="user_image_input" name="user_image"
									required="required" onchange="previewSelectedImage(this)">
							</div>

							<!-- Số điện thoại -->
							<div class="form-group mb-2">
								<label for="user_phone_number">Phone Number:<span
									class="rq">*</span></label> <input type="text"
									class="form-control" id="user_phone_number" name="user_phone_number"
									required="required">
							</div>

							<!-- Địa chỉ -->
							<div class="form-group mb-2">
								<label for="user_address">Address:<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="user_address" name="user_address" required="required">
							</div>

							<!-- Tên tài khoản -->
							<div class="form-group mb-2">
								<label for="user_account_name">Account Name:<span
									class="rq">*</span></label> <input type="text"
									class="form-control" id="user_account_name" name="user_account_name"
									required="required">
							</div>

							<!-- Mật khẩu tài khoản -->
							<div class="form-group mb-3">
								<label for="user_account_password">Account Password:<span
									class="rq">*</span></label>
								<div class="input-group align-items-center">
									<input type="password" class="form-control" id="user_account_password"
										name="user_account_password" required="required">
									<div class="input-group-append ms-1">
										<span class="input-group-text" onclick="togglePasswordVisibility()"> <i
												id="togglePassword" class="fas fa-eye"></i>
										</span>
									</div>
								</div>
							</div>

							<hr>
							<!-- Nút Submit -->
							<div class="mt-3">
							<button type="submit" class="btn btn-primary text-white">Thêm</button>
							<a href="${pageContext.request.contextPath}/user" class="btn btn-primary ml-4 text-white">Trở về</a>
							</div>
						</form>
					</div>

				</main>
			</div>
			<!-- Import footer -->
			<jsp:include page="component/footer.jsp" />
		</div>

	<script>
		function previewSelectedImage(input) {
			if (input.files && input.files[0]) {
				const reader = new FileReader();

				reader.onload = function (e) {
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