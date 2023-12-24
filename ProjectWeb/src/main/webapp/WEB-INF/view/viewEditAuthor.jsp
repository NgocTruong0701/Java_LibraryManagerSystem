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
<title>chỉnh sửa thông tin tác giả</title>
</head>


<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">
				<div class="form-container">
					<h2>Chỉnh sửa thông tin tác giả</h2>
					<hr>
					<form enctype="multipart/form-data"
						action="${pageContext.request.contextPath}/author/update"
						accept-charset="UTF-8" method="post">
						<!-- Thêm input hidden để giữ ID người dùng -->
						<div class="form-group mb-2">
							<input type="hidden" id="author_id" name="author_id"
								value="<%=request.getAttribute("author_id")%>" />
						</div>
						
						<div class="form-group mb-2">
							<label for="author_name">Tên tác giả:<span
									class="rq">*</span></label> <input type="text"
								class="form-control" id="author_name" name="author_name"
								value="<%=request.getAttribute("author_name")%>"
								required="required" />
						</div>
						
						<div class="form-group mb-2">
							<label for="author_date_of_birth">Ngày sinh:<span
									class="rq">*</span></label> <input
								type="date" class="form-control" id="author_date_of_birth"
								value="<%=request.getAttribute("author_date_of_birth")%>"
								name="author_date_of_birth" required="required">
						</div>

						<div class="form-group mb-2">
							<label for="author_description">Mô tả:<span
									class="rq">*</span></label> <input type="text"
								class="form-control" id="author_description" name="author_description"
								value="<%=request.getAttribute("author_description")%>"
								required="required">
						</div>
						
						<!-- Chọn hình ảnh -->
						<div class="form-group mb-2">
							<label for="author_image">Ảnh:</label> <img
								id="previewImage" 
								src="${pageContext.request.contextPath}/ImageDisplayServlet?imageName=<%=request.getAttribute("author_image")%>"
								alt="Author Image" width="150" height="150" class="border border-info"> <input
								type="file" class="form-control-file" id="author_image"
								name="author_image"
								onchange="previewSelectedImage(this)">
								<input
								type="hidden" id="author_image_old" name="author_image_old"
								value="<%=request.getAttribute("author_image")%>" />
						</div>
						
						<hr>
						<div class="mt-3">
							<!-- Nút Submit -->
							<button type="submit" class="btn btn-primary text-white">Cập nhật</button>
							<a href="${pageContext.request.contextPath}/author"
								class="btn btn-primary ml-6 text-white">Trở về</a>
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