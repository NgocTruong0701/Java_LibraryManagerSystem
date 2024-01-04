<%@page import="it602003.objects.UserObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand fw-bold text-success fs-1" href="${pageContext.request.contextPath}/trangchu">Library</a>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<span class="fw-bold fs-4 text-dark">THƯ VIỆN ĐẠI HỌC CÔNG
					NGHIỆP HÀ NỘI</span>
			</ul>
		</div>
		<form class="d-flex">
			<%
			UserObject user = (UserObject) request.getAttribute("user");
			if (user != null) {
			%>
			<a
				href="${pageContext.request.contextPath}/borrowing-form?user_id=<%=user.getUser_id()%>"
				class="fw-bold fs-6 text-dark">Xin chào, <%=user.getUser_name()%></a>
			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/login"
				class="fw-bold fs-6 text-dark">Đăng nhập</a>
			<%
			}
			%>
		</form>
	</div>
</nav>
<header
	class="bg-success d-flex flex-column justify-content-center align-items-center"
	style="height: 300px;" id="header"
	style="border-bottom: 3px solid yellow;">
	<div class="container text-center text-light">
		<div class="row">
			<p class="fs-4">HỆ THỐNG TRA CỨU TÀI LIỆU, VĂN BẢN, DỮ LIỆU...</p>
		</div>
		<div class="row">
			<form class="d-flex needs-validation" id="form_header"
				action="searchtrangchu" method="post">
				<select class="form-select search-input"
					aria-label="Disabled select example" id="select_search"
					name="select_search">
					<option value="4" selected>Tất cả</option>
					<option value="1">Nhan đề</option>
					<option value="2">Tác giả</option>
					<option value="3">Nhà xuất bản</option>
				</select>
				<div class="input-group">
					<input class="component_header form-control search-input"
						type="search" placeholder="Tìm kiếm sách, tài liệu, văn bản..."
						name="search_keyword" aria-label="Search" required>
					<button class="btn btn-light search-button " type="submit">
						Search</button>
				</div>
			</form>
		</div>
	</div>
</header>