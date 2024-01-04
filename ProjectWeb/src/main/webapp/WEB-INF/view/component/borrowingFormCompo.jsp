<%@page import="it602003.process.BorrowingForm"%>
<%@page import="it602003.objects.ReturnSlipObject"%>
<%@page import="it602003.process.ReturnSlip"%>
<%@page import="it602003.objects.UserObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.objects.BorrowingFormObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<main class="container-fluid">
	<div class="row g-4 mb-4">
		<div class="col-12 col-lg-6">
			<div class="app-card app-card-chart h-100 shadow-sm">
				<div class="app-card-header p-3">
					<div class="row justify-content-between align-items-center">
						<div class="col-auto">
							<h4 class="app-card-title">Danh sách phiếu mượn</h4>
						</div>
					</div>
					<!--//row-->
					<div class="app-card-body p-3 p-lg-4">
						<div class="mb-3 d-flex"></div>
						<div class="chart-container">

							<table class="table table-striped table-hover table-sm">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Ngày mượn</th>
										<th scope="col">Tiền kí gửi</th>
										<th scope="col">Hạn trả</th>
										<th scope="col">Người mượn</th>
										<th scope="col">Sách mượn</th>
										<%
										UserObject userr = (UserObject) request.getAttribute("user");
										if (userr != null) {
											if (userr.getUser_role() == 1) {
										%>
										<th scope="col" class="align-middle justify-content-center"
											colspan="2">Action</th>
										<%
										}
										}
										%>
									</tr>
								</thead>
								<tbody>
									<%
									int index = 0;
									ArrayList<BorrowingFormObject> brs = (ArrayList<BorrowingFormObject>) request.getAttribute("brs");
									int currentPage = (int) request.getAttribute("currentPage");
									int totalPages = (int) request.getAttribute("totalPages");
									// Assume users is a list of User objects retrieved from your backend
									for (BorrowingFormObject br : brs) {
									%>
									<tr>
										<th scope="row" class="align-middle"><%=index%></th>
										<td class="align-middle"><%=br.getBorrowing_form_date()%></td>
										<td class="align-middle"><%=br.getBorrowing_form_deposit()%></td>
										<td class="align-middle"><%=br.getBorrowing_form_due_date()%></td>
										<td class="align-middle"><%=br.getUser_id()%></td>
										<td class="align-middle"><%=br.getBook_id()%></td>
										<%
										if (userr != null) {
											if (userr.getUser_role() == 1) {
										%>
										<td class="align-middle"><a href="#"
											data-bs-toggle="modal"
											data-bs-target="#del<%=br.getBorrowing_form_id()%>"
											class="btn btn-outline-danger btn-sm"><i
												class="fa-regular fa-trash-can"></i> Xác nhận trả</a></td>
										<%
										}
										}
										%>
									</tr>
									<div class="modal fade" id="del<%=br.getBorrowing_form_id()%>"
										data-bs-backdrop="static" data-bs-keyboard="false"
										tabindex="-1"
										aria-labelledby="delLabel<%=br.getBorrowing_form_id()%>"
										aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header text-bg-danger bg-gradient">
													<h1 class="modal-title fs-5"
														id="delLabel<%=br.getBorrowing_form_id()%>">
														<i class="fa-solid fa-triangle-exclamation"></i> Xác nhận
														trả sách
													</h1>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body text-danger text-center">
													<h5>Bạn có thực sự muốn xác nhận người mượn trả sách</h5>
												</div>
												<div class="modal-footer text-bg-light bg-gradient">
													<form
														action="${pageContext.request.contextPath}/borrowing-form"
														method="post" id="deleteForm">
														<input type="hidden" id="deleteItemId" name="id"
															value="<%=br.getBorrowing_form_id()%>">
														<button type="submit" class="btn btn-danger">
															<i class="fa-solid fa-trash"></i> Xác nhận
														</button>
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">
															<i class="fa-solid fa-x"></i> Hủy
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>
									<%
									index++;
									}
									%>
								</tbody>
							</table>
							<div class="pagination align-items-center">
								<%-- Hiển thị các nút phân trang --%>
								<%
								for (int i = 1; i <= totalPages; i++) {
								%>
								<a href="${pageContext.request.contextPath}/user?page=<%= i %>"
									class="btn btn-primary me-2 text-white <%= (currentPage == i) ? "active" : "" %>">
									<%=i%>
								</a>
								<%
								}
								%>
								<b class="m-0"> Page: <%=currentPage%>/<%=totalPages%>
								</b>
							</div>

						</div>
					</div>
				</div>
			</div>
			<!--//app-card-->
		</div>
		<!--//col-->
		<div class="col-12 col-lg-6">
			<div class="app-card app-card-chart h-100 shadow-sm">
				<div class="app-card-header p-3">
					<div class="row justify-content-between align-items-center">
						<div class="col-auto">
							<h4 class="app-card-title">Danh sách phiếu trả</h4>
						</div>
					</div>
					<!--//row-->
				</div>
				<!--//app-card-header-->
				<div class="app-card-body p-3 p-lg-4">
					<div class="mb-3 d-flex"></div>
					<div class="chart-container">

						<table class="table table-striped table-hover table-sm">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Ngày trả</th>
									<th scope="col">Tiền trả kí gửi</th>
									<th scope="col">Tiền phạt quá hạn</th>
									<th scope="col">Người mượn</th>
									<th scope="col">Phiếu mượn</th>
								</tr>
							</thead>
							<tbody>
								<%
								int index1 = 0;
								ArrayList<ReturnSlipObject> rls = (ArrayList<ReturnSlipObject>) request.getAttribute("rls");
								int currentPage2 = (int) request.getAttribute("currentPage2");
								int totalPages2 = (int) request.getAttribute("totalPages2");
								// Assume users is a list of User objects retrieved from your backend
								for (ReturnSlipObject rl : rls) {
								%>
								<tr>
									<th scope="row" class="align-middle"><%=index%></th>
									<td class="align-middle"><%=rl.getReturn_slip_date()%></td>
									<td class="align-middle"><%=rl.getReturn_slip_refund()%></td>
									<td class="align-middle"><%=rl.getReturn_slip_late_fee()%></td>
									<td class="align-middle"><%=rl.getUser_id()%></td>
									<td class="align-middle"><%=rl.getBorrowing_form_id()%></td>
								</tr>
								<%
								index++;
								}
								%>
							</tbody>
						</table>
						<div class="pagination align-items-center">
							<%-- Hiển thị các nút phân trang --%>
							<%
							for (int i = 1; i <= totalPages2; i++) {
							%>
							<a href="${pageContext.request.contextPath}/user?page=<%= i %>"
								class="btn btn-primary me-2 text-white <%= (currentPage2 == i) ? "active" : "" %>">
								<%=i%>
							</a>
							<%
							}
							%>
							<b class="m-0"> Page: <%=currentPage2%>/<%=totalPages2%>
							</b>
						</div>

					</div>
				</div>
				<!--//app-card-body-->
			</div>
			<!--//app-card-->
		</div>
		<!--//col-->
	</div>


	<div class="app-card-body d-flex justify-content-center align-center p-3 p-lg-4">
		<div class="mb-3 d-flex"></div>
		<div class="chart-container">
			<canvas id="canvas-doughnut"></canvas>
		</div>
	</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<%
BorrowingForm b = new BorrowingForm();
ReturnSlip r = new ReturnSlip();

int totalBorrowingForms = b.getTotalBorrowingForms();
int totalReturnSlip = r.getTotalReturnSlip();
%>
<script>
	// Hàm vẽ biểu đồ
	function barChart() {
		var label = [ 'Phiếu mượn', 'Phiếu trả' ];
		var countType = [
<%=totalBorrowingForms%>
	,
<%=totalReturnSlip%>
	];
		var ctx = document.getElementById('canvas-doughnut').getContext('2d');
		var myChart = new Chart(ctx, {
			type : 'doughnut',
			data : {
				labels : label,
				datasets : [ {
					label : 'Number of Books',
					data : countType,
					backgroundColor : [ 'red', 'blue' ],
					borderColor : [ 'rgb(255, 99, 132)', 'rgb(255, 159, 64)',
							'rgb(255, 205, 86)', 'rgb(75, 192, 192)',
							'rgb(54, 162, 235)', 'rgb(153, 102, 255)',
							'rgb(201, 203, 207)' ],
					borderWidth : 1,
					hoverOffset : 4
				} ]
			},
			options : {
				scales : {
					y : {
						beginAtZero : true
					}
				}
			}
		});
	}

	// Gọi hàm vẽ biểu đồ khi trang đã được tải hoàn toàn
	document.addEventListener('DOMContentLoaded', function() {
		barChart();
	});
</script>