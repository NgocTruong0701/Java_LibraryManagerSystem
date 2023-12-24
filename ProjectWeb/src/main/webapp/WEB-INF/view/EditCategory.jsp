<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />

<style>
.rq {
	color: red;
}
</style>
<script>
	function my_submit() {

	}
	function go_to_CategoryView() {
		window.location.href = "categoryview";
	}
</script>


</head>
<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">

				<h2>Chỉnh sửa danh mục</h2>
				<hr>
				<div class="mt-4">
					<form class="row g-3 needs-validation" action="categoryedit"
						method="post" accept-charset="UTF-8">
						<div class="row">
							<input type="hidden" name="category_id"
								value="${ categoryObject.getCategory_id() }">
							<div class="col">
								<label for="category_name" class="form-label">Tên danh
									mục<span class="rq">*</span>
								</label> <input type="text" class="form-control" id="category_name"
									name="category_name" required
									value="${ categoryObject.getCategory_name() }">
							</div>
						</div>
						<div class="row">
							<!-- 
				<div class="col">
				    <label for="category_total_book" class="form-label">Tổng số sách<span class="rq">*</span></label>
				    <input type="number" class="form-control" id="category_total_book" name="category_total_book" required value="${ categoryObject.getCategory_total_book() }">
				  </div>
			  	</div>
			   -->
							<div class="row d-flex mt-3">
								<hr>
								<button class="btn btn-success col text-white" type="submit">Xác
									nhận sửa danh mục</button>
								<button class="btn btn-primary col text-white" type="reset">Đặt
									lại</button>
								<button class="btn btn-danger col text-white" type="button"
									onclick="go_to_CategoryView()">Hủy</button>
							</div>
					</form>
				</div>

			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</html>