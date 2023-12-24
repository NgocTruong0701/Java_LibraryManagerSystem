<%@page import="it602003.objects.CategoryObject"%>
<%@page import="it602003.process.Category"%>
<%@page import="it602003.process.Author"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.objects.AuthorObject"%>
<%@page import="it602003.objects.BookObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">
				<h1 class="app-page-title">Overview</h1>

				<div class="row g-4 mb-4">
					<div class="col-6 col-lg-3">
						<div class="app-card app-card-stat shadow-sm h-100">
							<div class="app-card-body p-3 p-lg-4">
								<h4 class="stats-type mb-1">Số lượng sách</h4>
								<div class="stats-figure"><%=request.getAttribute("totalBooks")%></div>
								<div class="stats-meta text-success">
									<svg width="1em" height="1em" viewBox="0 0 16 16"
										class="bi bi-arrow-up" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
</svg>
									20%
								</div>
							</div>
							<!--//app-card-body-->
							<a class="app-card-link-mask"
								href="${ pageContext.request.contextPath }/bookview"></a>
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->

					<div class="col-6 col-lg-3">
						<div class="app-card app-card-stat shadow-sm h-100">
							<div class="app-card-body p-3 p-lg-4">
								<h4 class="stats-type mb-1">Số lượng người đọc</h4>
								<div class="stats-figure"><%=request.getAttribute("totalUsers")%>
								</div>
								<div class="stats-meta text-success">
									<svg width="1em" height="1em" viewBox="0 0 16 16"
										class="bi bi-arrow-up" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd"
											d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z" />
</svg>
									10%
								</div>
							</div>
							<!--//app-card-body-->
							<a class="app-card-link-mask"
								href="${ pageContext.request.contextPath }/user"></a>
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
					<div class="col-6 col-lg-3">
						<div class="app-card app-card-stat shadow-sm h-100">
							<div class="app-card-body p-3 p-lg-4">
								<h4 class="stats-type mb-1">Tổng phiếu mượn</h4>
								<div class="stats-figure">23</div>
								<div class="stats-meta">Open</div>
							</div>
							<!--//app-card-body-->
							<a class="app-card-link-mask" href="#"></a>
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
					<div class="col-6 col-lg-3">
						<div class="app-card app-card-stat shadow-sm h-100">
							<div class="app-card-body p-3 p-lg-4">
								<h4 class="stats-type mb-1">Tổng phiếu trả</h4>
								<div class="stats-figure">6</div>
								<div class="stats-meta">New</div>
							</div>
							<!--//app-card-body-->
							<a class="app-card-link-mask" href="#"></a>
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
				</div>
				<!--//row-->

				<div class="row g-4 mb-4">
					<div class="col-12 col-lg-6">
						<div class="app-card app-card-chart h-100 shadow-sm">
							<div class="app-card-header p-3">
								<div class="row justify-content-between align-items-center">
									<div class="col-auto">
										<h4 class="app-card-title">Line Chart Example</h4>
									</div>
									<!--//col-->
									<div class="col-auto">
										<div class="card-header-action">
											<a href="charts.html">More charts</a>
										</div>
										<!--//card-header-actions-->
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body p-3 p-lg-4">
								<div class="mb-3 d-flex">
								</div>
								<div class="chart-container">
									<canvas id="canvas-barchart"></canvas>
								</div>
							</div>
							<!--//app-card-body-->
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
					<div class="col-12 col-lg-6">
						<div class="app-card app-card-chart h-100 shadow-sm">
							<div class="app-card-header p-3">
								<div class="row justify-content-between align-items-center">
									<div class="col-auto">
										<h4 class="app-card-title">Bar Chart Example</h4>
									</div>
									<!--//col-->
									<div class="col-auto">
										<div class="card-header-action">
											<a href="charts.html">More charts</a>
										</div>
										<!--//card-header-actions-->
									</div>
									<!--//col-->
								</div>
								<!--//row-->
							</div>
							<!--//app-card-header-->
							<div class="app-card-body p-3 p-lg-4">
								<div class="mb-3 d-flex">
								</div>
								<div class="chart-container">
									<canvas id="canvas-piechart"></canvas>
								</div>
							</div>
							<!--//app-card-body-->
						</div>
						<!--//app-card-->
					</div>
					<!--//col-->
				</div>
			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
    // Hàm vẽ biểu đồ
    function barChart() {
        var authorLabels = [];
        var bookCountByAuthor = [];
        <%Author a = new Author();%>
        <%for (AuthorObject author : (ArrayList<AuthorObject>) request.getAttribute("authors")) {%>
            authorLabels.push('<%=author.getAuthor_name()%>'); 
            bookCountByAuthor.push(<%=a.getBookTotal(author.getAuthor_id())%>); // Sử dụng phương thức getBookTotal để lấy số lượng sách của mỗi tác giả
        <%}%>

        var ctx = document.getElementById('canvas-barchart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: authorLabels, // Sử dụng mảng chứa tên của các tác giả
                datasets: [{
                    label: 'Number of Books',
                    data: bookCountByAuthor, // Sử dụng mảng chứa số lượng sách của mỗi tác giả
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(255, 205, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(201, 203, 207, 0.2)'
                      ],
                      borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 159, 64)',
                        'rgb(255, 205, 86)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)',
                        'rgb(201, 203, 207)'
                      ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // Gọi hàm vẽ biểu đồ khi trang đã được tải hoàn toàn
    document.addEventListener('DOMContentLoaded', function () {
    	barChart();
    });
</script>
<script>
    function pieChart() {
        var categoryLabels = [];
        var bookCountByCategory = [];
        <% Category c = new Category(); %>
        <% for (CategoryObject category : (ArrayList<CategoryObject>) request.getAttribute("categories")) { %>
            categoryLabels.push('<%= category.getCategory_name() %>');
            bookCountByCategory.push(<%= c.getBookTotal(category.getCategory_id()) %>); // Đóng dấu ngoặc cho hàm push
        <% } %>

        var ctx2 = document.getElementById('canvas-piechart').getContext('2d');
        var myChart = new Chart(ctx2, {
            type: 'line',
            data: {
                labels: categoryLabels,
                datasets: [{
                    label: 'Number of Books',
                    data: bookCountByCategory,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(255, 205, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(201, 203, 207, 0.2)'
                    ],
                    borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 159, 64)',
                        'rgb(255, 205, 86)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)',
                        'rgb(201, 203, 207)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    // Gọi hàm vẽ biểu đồ khi trang đã được tải hoàn toàn
    document.addEventListener('DOMContentLoaded', function () {
        pieChart();
    });
</script>


</body>
</html>