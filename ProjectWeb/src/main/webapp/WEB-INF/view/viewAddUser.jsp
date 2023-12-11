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
				<div class="form-container">
					<h2>Thêm Người Dùng</h2>
					<form enctype="multipart/form-data" action="process_form.php"
						method="post">
						<!-- Tên người dùng -->
						<div class="form-group">
							<label for="user_name">User Name:</label> <input type="text"
								class="form-control" id="user_name" name="user_name">
						</div>

						<!-- Chọn hình ảnh -->
						<div class="form-group">
							<label for="user_image">User Image:</label> <input type="file"
								class="form-control-file" id="user_image" name="user_image">
						</div>

						<!-- Số điện thoại -->
						<div class="form-group">
							<label for="user_phone_number">Phone Number:</label> <input
								type="text" class="form-control" id="user_phone_number"
								name="user_phone_number">
						</div>

						<!-- Địa chỉ -->
						<div class="form-group">
							<label for="user_address">Address:</label> <input type="text"
								class="form-control" id="user_address" name="user_address">
						</div>

						<!-- Tên tài khoản -->
						<div class="form-group">
							<label for="user_account_name">Account Name:</label> <input
								type="text" class="form-control" id="user_account_name"
								name="user_account_name">
						</div>

						<!-- Mật khẩu tài khoản -->
						<div class="form-group">
							<label for="user_account_password">Account Password:</label> <input
								type="password" class="form-control" id="user_account_password"
								name="user_account_password">
						</div>

						<!-- Nút Submit -->
						<button type="submit" class="btn btn-primary">Thêm</button>
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
</body>
</html>