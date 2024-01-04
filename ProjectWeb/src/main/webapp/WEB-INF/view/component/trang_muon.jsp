<%@page import="it602003.process.BorrowingForm"%>
<%@page import="it602003.objects.UserObject"%>
<%@page import="it602003.process.Publisher"%>
<%@page import="it602003.process.Author"%>
<%@page import="it602003.process.Category"%>
<%@page import="it602003.objects.BookObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BookObject book = (BookObject) request.getAttribute("book_trang_muon");
Category category = new Category();
Author author = new Author();
Publisher publisher = new Publisher();
BorrowingForm b = new BorrowingForm();
%>
<div class="mt-5">
	<div class="container">
		<div class="row">
			<div class="col-md-9 card">
				<div class="card border-0"
					style="max-width: 600px; max-height: 400px;">
					<div class="row g-0">
						<div class="col-4">
							<img
								src="ImageDisplayServlet?imageName=<%=book.getBook_image()%>"
								alt="Card Image" class="img-fluid"
								style="height: 270px; width: 100%">
						</div>
						<div class="col-8">
							<div class="card-body">
								<h5 class="card-title"><%=book.getBook_name()%></h5>
								<p class="card-text">
									Danh mục:
									<%=category.searchCategory(book.getCategory_id()).getCategory_name()%></p>
								<p class="card-text">
									Tác giả:
									<%=author.searchBook(book.getAuthor_id()).getAuthor_name()%></p>
								<p class="card-text">
									Nhà xuất bản:
									<%=publisher.searchPublisher(book.getPublisher_id()).getPublisher_name()%></p>
								<p class="card-text">
									Năm xuất bản:
									<%=book.getBook_publishing_year()%></p>
								<p class="card-text">
									Giá: <span class="text-danger"><%=book.getBook_price()%></span>
									Đ
								</p>
							</div>
						</div>
					</div>
				</div>
				<table class="table table-sm mt-4">
					<tbody>
						<tr>
							<th scope="row">Ngôn ngữ</th>
							<td><%=book.getBook_language()%></td>
						</tr>
						<tr>
							<th scope="row">Tác giả</th>
							<td><%=author.searchBook(book.getAuthor_id()).getAuthor_name()%></td>

						</tr>
						<tr>
							<th scope="row">Thông tin nhan đề</th>
							<td><%=book.getBook_name()%></td>
						</tr>
						<tr>
							<th scope="row">Xuất bản, Phát hành</th>
							<td><%=publisher.searchPublisher(book.getPublisher_id()).getPublisher_name()%>,
								<%=book.getBook_publishing_year()%></td>

						</tr>
						<tr>
							<th scope="row">Số trang</th>
							<td><%=book.getBook_page_number()%></td>
						</tr>
					</tbody>
				</table>

				<div class="card">
					<h5 class="text-uppercase card-title">Thông tin xếp giá</h5>
					<div class="card-body">
						<p class="card-text">
							Tổng số bản:
							<%=(b.countBookInBorrowingById(book.getBook_id()) + book.getBook_inventory_number())%>
						</p>
						<p>
							Tổng số bản rỗi:
							<%=book.getBook_inventory_number()%>
						</p>
						<p>
							Tổng số đang đặt chỗ:
							<%=b.countBookInBorrowingById(book.getBook_id())%>
						</p>
					</div>
				</div>
				<%
				UserObject userr = (UserObject) request.getAttribute("user");
				if (userr == null) {
				%>
				<div class="row card">

					<div class="col-md-6 card-body">
						<form action="${ pageContext.request.contextPath }/trangmuon"
							enctype="multipart/form-data" accept-charset="UTF-8"
							method="post">
							<h5 class="text-uppercase">Đặt mượn</h5>
							<p>Tài khoản</p>
							<input type="text" name="taiKhoan" class="form-control">
							<p>Mật khẩu</p>
							<input type="password" name="matKhau" class="form-control">
							<input type="text" name="bookId" class="form-control" hidden
								value="<%=book.getBook_id()%>">
							<div class="text-center" style="margin-top: 5px;">
								<button type="submit" class="btn btn-success">Đặt Mượn</button>
							</div>
						</form>
					</div>
					<div class="col-md-6 col-sm-0"></div>
				</div>
			</div>
			<%
			}
			%>
			<%
			if (userr != null) {
			%>
			<div class="row card">

				<div class="col-md-6 card-body">
					<form action="${ pageContext.request.contextPath }/trangmuon"
						enctype="multipart/form-data" accept-charset="UTF-8" method="post">
						<h5 class="text-uppercase">Đặt mượn</h5>
						<input type="text" name="userId" class="form-control" hidden
							value="<%=userr.getUser_id()%>"> <input type="text"
							name="bookId" class="form-control" hidden
							value="<%=book.getBook_id()%>" />
						<div class="text-center" style="margin-top: 5px;">
							<button type="submit" class="btn btn-success">Đặt Mượn</button>
						</div>
					</form>
				</div>
				<div class="col-md-6 col-sm-0"></div>
			</div>
		</div>
		<%
		}
		%>
		<div class="col-md-3"></div>
	</div>
</div>
</div>