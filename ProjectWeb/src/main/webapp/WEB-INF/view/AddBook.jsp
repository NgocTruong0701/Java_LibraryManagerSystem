<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it602003.process.*"%>
<%@ page import="it602003.objects.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Thêm sách mới</title>
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
	function go_to_BookView() {
		window.location.href = "bookview";
	}
</script>

</head>
<body class="app">
	<%
	Author author = new Author();
	Category category = new Category();
	Publisher publisher = new Publisher();
	%>
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">

				<h2>Thêm sách mới</h2>
				<hr>
				<div class="mt-4">
					<form class="row g-3 needs-validation" action="bookaddcontrol"
						id="book_add_form" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-6">
								<label for="tenSach" class="form-label">Tên sách<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="tenSach" name="tenSach" required>
							</div>
							<div class="col-6">
								<label for="namXuatBan" class="form-label">Năm xuất bản<span
									class="rq">*</span></label> <input type="number" min="0" max="3000"
									class="form-control" id="namXuatBan" name="namXuatBan" required>
							</div>
						</div>
						<div class="row">
							<div class="col-6">
								<label for="hinhAnh" class="form-label">Hình ảnh<span
									class="rq">*</span></label> <input type="file" class="form-control"
									id="hinhAnh" name="hinhAnh" accept="image/*" required>
							</div>
							<div class="col-6">
								<label for="gia" class="form-label">Giá<span class="rq">*</span></label>
								<input type="number" class="form-control" id="gia" name="gia"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<label for="soLuongTonKho" class="form-label">Số lượng
									tồn kho<span class="rq">*</span>
								</label> <input type="number" class="form-control" id="soLuongTonKho"
									name="soLuongTonKho" required>
							</div>
							<div class="col-4">
								<label for="soTrang" class="form-label">Số trang<span
									class="rq">*</span></label> <input type="number" class="form-control"
									id="soTrang" name="soTrang" required>
							</div>
							<div class="col-4">
								<label for="tinhTrang" class="form-label">Tình trạng<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="tinhTrang" name="tinhTrang" required>
							</div>

						</div>
						<div class="row">
							<div class="col-4">
								<label for="ngonNgu" class="form-label">Ngôn ngữ<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="ngonNgu" name="ngonNgu" required>
							</div>
							<div class="col-4">
								<label for="maTacGia" class="form-label">Tác giả<span
									class="rq">*</span></label> <select class="form-select" id="maTacGia"
									name="maTacGia" required>
									<%
									// Lấy danh sách tác giả từ CSDL (sử dụng phương thức tương ứng trong class Author)
									ArrayList<AuthorObject> authors = author.getAllAuthors();
									for (AuthorObject author1 : authors) {
									%>
									<option value="<%=author1.getAuthor_id()%>"><%=author1.getAuthor_name()%></option>
									<%
									}
									%>
								</select>
							</div>

							<!-- Dropdown for Category -->
							<div class="col-4">
								<label for="maDanhMuc" class="form-label">Danh mục<span
									class="rq">*</span></label> <select class="form-select" id="maDanhMuc"
									name="maDanhMuc" required>
									<%
									// Lấy danh sách danh mục từ CSDL (sử dụng phương thức tương ứng trong class Category)
									ArrayList<CategoryObject> categories = category.getAllCategories();
									for (CategoryObject category1 : categories) {
									%>
									<option value="<%=category1.getCategory_id()%>"><%=category1.getCategory_name()%></option>
									<%
									}
									%>
								</select>
							</div>
						</div>
						<div class="row">
							<!-- Dropdown for Publisher -->
							<div class="col-6">
								<label for="maNhaXuatBan" class="form-label">Nhà xuất
									bản<span class="rq">*</span>
								</label> <select class="form-select" id="maNhaXuatBan"
									name="maNhaXuatBan" required>
									<%
									// Lấy danh sách nhà xuất bản từ CSDL (sử dụng phương thức tương ứng trong class Publisher)
									ArrayList<PublisherObject> publishers = publisher.getAllPublishers();
									for (PublisherObject publisher1 : publishers) {
									%>
									<option value="<%=publisher1.getPublisher_id()%>"><%=publisher1.getPublisher_name()%></option>
									<%
									}
									%>
								</select>
							</div>


							<div class="col-6">
								<label for="moTa" class="form-label">Mô tả<span
									class="rq">*</span></label>
								<textarea rows="5" cols="30" class="form-control" id="moTa"
									name="moTa" required></textarea>
							</div>
						</div>


						<div class="row d-flex mt-3">
							<hr>
							<button class="btn btn-success col text-white" type="submit">Xác
								nhận thêm sách</button>
							<button class="btn btn-primary col text-white" type="reset">Đặt lại</button>
							<button class="btn btn-danger col text-white" type="button"
								onclick="go_to_BookView()">Hủy</button>
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