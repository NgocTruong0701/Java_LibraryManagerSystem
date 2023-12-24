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
	function go_to_PublisherView() {
		window.location.href = "publisherview";
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
				<div class="form-container">
					<h2>Chỉnh sửa nhà xuất bản</h2>
					<hr>
					<form class="row g-3 needs-validation" action="publisheredit"
						method="post" accept-charset="UTF-8">
						<div class="row">
							<input type="hidden" name="publisher_id"
								value="${ publisherObject.getPublisher_id() }">
							<div class="col">
								<label for="publisher_name" class="form-label">Publisher
									Name<span class="rq">*</span>
								</label> <input type="text" class="form-control" id="publisher_name"
									name="publisher_name" required
									value="${ publisherObject.getPublisher_name() }">
							</div>
							<div class="col">
								<label for="publisher_phone_number" class="form-label">Phone
									Number<span class="rq">*</span>
								</label> <input type="text" class="form-control"
									id="publisher_phone_number" name="publisher_phone_number"
									required
									value="${ publisherObject.getPublisher_phone_number() }">
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="publisher_address" class="form-label">Address<span
									class="rq">*</span></label> <input type="text" class="form-control"
									id="publisher_address" name="publisher_address" required
									value="${ publisherObject.getPublisher_address() }">
							</div>
						</div>
						<div class="row d-flex mt-1 mt-4">
							<hr>
							<button class="btn btn-success col text-white" type="submit">Lưu</button>
							<button class="btn btn-primary col text-white" type="reset">Reset</button>
							<button class="btn btn-danger col text-white" type="button"
								onclick="go_to_PublisherView()">Thoát</button>
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