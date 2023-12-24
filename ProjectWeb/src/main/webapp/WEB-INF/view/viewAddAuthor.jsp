<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Head content -->
<!-- Import Bootstrap CSS -->
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
<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">
				<div class="form-container">
					<h2>Thêm tác giả</h2>
					<hr>
					<form enctype="multipart/form-data"
						action="${pageContext.request.contextPath}/author/create"
						accept-charset="UTF-8" method="post">
						<div class="form-group mb-2">
							<label for="author_name">Tên tác giả:<span
									class="rq">*</span></label> <input type="text"
								class="form-control" id="author_name" name="author_name"
								required="required" />
						</div>

						<div class="form-group mb-2">
							<label for="author_date_of_birth">Ngày sinh:<span
									class="rq">*</span></label> <input
								type="date" class="form-control" id="author_date_of_birth"
								name="author_date_of_birth" required="required">
						</div>

						<div class="form-group mb-2">
							<label for="author_description">Mô tả:<span
									class="rq">*</span></label> <input type="text"
								class="form-control" id="author_description" name="author_description"
								required="required">
						</div>
						
						<!-- Chọn hình ảnh -->
						<div class="form-group mb-2">
							<label for="author_image">Ảnh:<span
									class="rq">*</span></label> <img
								id="previewImage" src="/lnt.library/image/default-avatar.png"
								alt="Author Image" width="150" height="150" class="border border-info"> <input
								type="file" class="form-control-file" id="author_image"
								name="author_image" required="required"
								onchange="previewSelectedImage(this)">
						</div>
						
						<hr>
						<!-- Nút Submit -->
						<div class="mt-3">
						<button type="submit" class="btn btn-primary text-white">Thêm</button>
						<a href="${pageContext.request.contextPath}/author"
							class="btn btn-primary ml-4 text-white">Trở về</a>
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

				reader.onload = function(e) {
					document.getElementById('previewImage').setAttribute('src',
							e.target.result);
				};

				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
</html>