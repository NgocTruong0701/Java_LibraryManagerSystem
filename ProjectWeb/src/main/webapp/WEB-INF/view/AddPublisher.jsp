<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 5 Layout</title>
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
					<h2>Thêm nhà xuất bản</h2>
					<hr>
					<div class="mt-4">
						<form class="row g-3 needs-validation" action="publisheradd"
							method="post" accept-charset="UTF-8">
							<div class="row">
								<div class="col">
									<label for="publisher_name" class="form-label">Name<span
										class="rq">*</span></label> <input type="text" class="form-control"
										id="publisher_name" name="publisher_name" required>
								</div>
								<div class="col">
									<label for="publisher_phone_number" class="form-label">Phone
										Number<span class="rq">*</span>
									</label> <input type="text" class="form-control"
										id="publisher_phone_number" name="publisher_phone_number"
										required>
								</div>
							</div>

							<div class="row ">
								<div class="col">
									<label for="publisher_address" class="form-label">Address<span
										class="rq">*</span></label> <input type="text" class="form-control"
										id="publisher_address" name="publisher_address" required>
								</div>
							</div>


							<div class="row d-flex mt-4">
								<hr>
								<button class="btn btn-success col" type="submit">Thêm</button>
								<button class="btn btn-primary col" type="reset">Reset</button>
								<button class="btn btn-danger col" type="button"
									onclick="go_to_PublisherView()">Trở về</button>
							</div>
						</form>
					</div>
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