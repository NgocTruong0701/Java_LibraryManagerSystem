<%@page import="it602003.objects.BookObject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<BookObject> books_new = (ArrayList<BookObject>) request.getAttribute("books_new");
ArrayList<BookObject> books_muon_nhieu = (ArrayList<BookObject>) request.getAttribute("books_muon_nhieu");
%>
<div class="content-section">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-sm-12 mb-3" id="noidung1">
				<!-- Nội dung phần 1 -->
				<h5 class="text-uppercase">Sách mới</h5>
				<div class="row">
					<%
					for (BookObject book : books_new) {
					%>
					<div class="card col-md-4 col-sm-3 m-1"
						style="width: 11rem; height: 280px;">
						<img
							src="ImageDisplayServlet?imageName=<%=book.getBook_image()%>"
							class="card-img-top"
							style="width: 100%; height: 63%; object-fit: cover;" alt="...">
						<div class="card-footer">
							<p class="card-text"
								style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
								<a href="trangmuon?bid=<%=book.getBook_id()%>"
									class="card-link fs-6"><%=book.getBook_name()%></a>
							</p>
							<span class="text-danger">Giá: <%=book.getBook_price()%></span>
							<br> <span class="text-success">Số sách sẳn: <%=book.getBook_inventory_number()%></span>
						</div>
					</div>
					<%
					}
					%>
				</div>
				<div class="row mt-5 d-flex justify-content-center">
					<a href="#"
						class="btn btn-success col-3 text-decoration-none text-light">Xem
						tất cả</a>
				</div>
			</div>
			<div class="col-md-6 col-sm-12 mb-3" id="noidung2">
				<!-- Nội dung phần 2 -->
				<h5 class="text-uppercase">Sách được mượn nhiều</h5>
				<div class="row">
					<%
					for (BookObject book : books_new) {
					%>
					<div class="card col-md-4 col-sm-3 m-1"
						style="width: 11rem; height: 280px;">
						<img
							src="ImageDisplayServlet?imageName=<%=book.getBook_image()%>"
							class="card-img-top"
							style="width: 100%; height: 63%; object-fit: cover;" alt="...">
						<div class="card-footer">
							<p class="card-text"
								style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
								<a href="trangmuon?bid=<%=book.getBook_id()%>"
									class="card-link fs-6"><%=book.getBook_name()%></a>
							</p>
							<span class="text-danger">Giá: <%=book.getBook_price()%></span>
							<br> <span class="text-success">Số sách sẳn: <%=book.getBook_inventory_number()%></span>
						</div>
					</div>
					<%
					}
					%>
				</div>
				<div class="row mt-5 d-flex justify-content-center">
					<a href="#"
						class="btn btn-success col-3 text-decoration-none text-light">Xem
						tất cả</a>
				</div>
			</div>
		</div>
	</div>
</div>