<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="it602003.objects.*"%>
<%@ page import="it602003.process.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Chỉnh sửa thông tin sách</title>
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
	BookObject book2 = (BookObject) request.getAttribute("book1");
	%>
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
				<h2>Chỉnh sửa thông tin sách</h2>
				<hr>
				<div>
					<form action="${pageContext.request.contextPath}/bookedit"
						accept-charset="UTF-8" method="post" enctype="multipart/form-data">
						<div class="row d-flex">
							<label for="maSach1" class="form-label">Mã sách<span
								class="rq col">*</span></label>
							<p class="col"><%=book2.getBook_id()%></p>
							<input type="hidden" id="maSach1" name="maSach1"
								value="<%=book2.getBook_id()%>" class="col">
						</div>
						<div class="row">
							<div class="col-6">
								<label for="tenSach1" class="form-label">Tên sách<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="tenSach1" name="tenSach1" value="<%=book2.getBook_name()%>"
									required>
							</div>
							<div class="col-6">
								<label for="namXuatBan1" class="form-label">Năm xuất bản<span
									class="rq">*</span></label> <input type="number" min="0" max="3000"
									class="form-control" id="namXuatBan1" name="namXuatBan1"
									value="<%=book2.getBook_publishing_year()%>" required>
							</div>
						</div>
						<div class="row">
							<!-- Dropdown for Publisher -->
							<div class="col-6">
								<label for="maNhaXuatBan1" class="form-label">Nhà xuất
									bản<span class="rq">*</span>
								</label> <select class="form-select" id="maNhaXuatBan1"
									name="maNhaXuatBan1" required>
									<%
									// Lấy danh sách nhà xuất bản từ CSDL (sử dụng phương thức tương ứng trong class Publisher)
									ArrayList<PublisherObject> publishers = publisher.getAllPublishers();
									for (PublisherObject publisher1 : publishers) {
									%>
									<option value="<%=publisher1.getPublisher_id()%>"
										<%=(publisher1.getPublisher_id() == book2.getPublisher_id()) ? "selected" : ""%>><%=publisher1.getPublisher_name()%></option>
									<%
									}
									%>
								</select>
							</div>



							<div class="col-6">
								<label for="gia1" class="form-label">Giá<span class="rq">*</span></label>
								<input type="number" class="form-control" id="gia1" name="gia1"
									value="<%=book2.getBook_price()%>" required>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<label for="soLuongTonKho1" class="form-label">Số lượng
									tồn kho<span class="rq">*</span>
								</label> <input type="number" class="form-control" id="soLuongTonKho1"
									name="soLuongTonKho1"
									value="<%=book2.getBook_inventory_number()%>" required>
							</div>
							<div class="col-4">
								<label for="soTrang1" class="form-label">Số trang<span
									class="rq">*</span></label> <input type="number" class="form-control"
									id="soTrang1" name="soTrang1"
									value="<%=book2.getBook_page_number()%>" required>
							</div>
							<!-- Dropdown for Category -->
							<div class="col-4">
								<label for="maDanhMuc1" class="form-label">Danh mục<span
									class="rq">*</span></label> <select class="form-select" id="maDanhMuc1"
									name="maDanhMuc1" required>
									<%
									// Lấy danh sách danh mục từ CSDL (sử dụng phương thức tương ứng trong class Category)
									ArrayList<CategoryObject> categories = category.getAllCategories();
									for (CategoryObject category1 : categories) {
									%>
									<option value="<%=category1.getCategory_id()%>"
										<%=(category1.getCategory_id() == book2.getCategory_id()) ? "selected" : ""%>><%=category1.getCategory_name()%></option>
									<%
									}
									%>
								</select>
							</div>


						</div>
						<div class="row">
							<div class="col-4">
								<label for="tinhTrang1" class="form-label">Tình trạng<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="tinhTrang1" name="tinhTrang1"
									value="<%=book2.getBook_status()%>" required>
							</div>
							<div class="col-4">
								<label for="ngonNgu1" class="form-label">Ngôn ngữ<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="ngonNgu1" name="ngonNgu1"
									value="<%=book2.getBook_language()%>" required>
							</div>
							<div class="col-4">
								<label for="maTacGia1" class="form-label">Tác giả<span
									class="rq">*</span></label> <select class="form-select" id="maTacGia1"
									name="maTacGia1" required>
									<%
									// Lấy danh sách tác giả từ CSDL (sử dụng phương thức tương ứng trong class Author)
									ArrayList<AuthorObject> authors = author.getAllAuthors();
									for (AuthorObject author1 : authors) {
									%>
									<option value="<%=author1.getAuthor_id()%>"
										<%=(author1.getAuthor_id() == book2.getAuthor_id()) ? "selected" : ""%>><%=author1.getAuthor_name()%></option>
									<%
									}
									%>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-6">
								<label for="moTa1" class="form-label">Mô tả<span
									class="rq">*</span></label>
								<textarea rows="5" cols="30" class="form-control" id="moTa1"
									name="moTa1" required><%=book2.getBook_description()%></textarea>
							</div>
							<div class="col-6 form-group mb-2">
								<label for="hinhAnh1" class="form-label">Hình ảnh<span
									class="rq">*</span></label> <input type="file" class="form-control"
									id="hinhAnh1" name="hinhAnh1" accept="image/*"> <img
									id="selectedImage" alt="Selected Image"
									style="max-width: 100px; max-height: 100px;"
									src="ImageDisplayServlet?imageName=<%=book2.getBook_image()%>" />
							</div>
						</div>

						<div class="row d-flex mt-3">
						<hr>
							<button class="col btn btn-success child text-white"
								type="submit">Xác nhận sửa sách</button>
							<button class="col btn btn-primary child text-white" type="reset">Đặt
								lại</button>
							<button class="col btn btn-danger child text-white" type="button"
								onclick="go_to_BookView()">Hủy</button>
						</div>
					</form>
				</div>

			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>
	<script>
		// Lắng nghe sự kiện change của input type="file"
		document
				.getElementById('hinhAnh1')
				.addEventListener(
						'change',
						function(event) {
							// Lấy danh sách các files được chọn
							const files = event.target.files;

							// Kiểm tra xem có files được chọn hay không
							if (files.length > 0) {
								// Lấy thông tin của file đầu tiên trong danh sách
								const selectedFile = files[0];

								// Tạo FileReader để đọc nội dung của file
								const reader = new FileReader();

								// Xử lý khi đọc file thành công
								reader.onload = function(e) {
									// Lấy đường dẫn tạm thời của file (ở dạng base64)
									const imageDataUrl = e.target.result;

									// Đặt đường dẫn của ảnh cho thuộc tính src của thẻ img
									document.getElementById('selectedImage').src = imageDataUrl;
								};

								// Đọc nội dung của file dưới dạng base64
								reader.readAsDataURL(selectedFile);
							}
						});
	</script>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</html>