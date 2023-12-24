<%@page import="it602003.process.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="it602003.objects.BookObject"%>
<%@page import="it602003.process.Book"%>
<%@page import="it602003.process.Category" %>
<%@page import="it602003.process.Publisher" %>
<%@ page import="it602003.process.Author" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Book list</title>
	<style>
		.scrollable-frame {
            max-height: 600px; /* Set the maximum height for the scrollable frame */
            overflow-y: auto; /* Enable vertical scrolling */
        }
				
	</style>
</head>
<body class="app">
	<!-- Import header -->
	<jsp:include page="component/header.jsp" />
	<div class="app-wrapper">
		<div class="app-content pt-3 p-md-3 p-lg-4">
			<!-- Main content -->
			<main class="container-fluid">
				<!--Search  -->
				<div class="container-fluid border border-primary p-3 mb-3">
					<h4 id="#scrollsearchbook">Search Book</h4>
					<form action="booksearch" class="needs-validation">
						<div class="row">
							<div class="col-3">
								<select class="form-select" aria-label="Default select example" name="luaChonSearch">
									<option value="1" selected>ID</option>
									<option value="2">Name</option>
									<option value="3">Author</option>
								</select>

							</div>
							<div class="col-9">
								<div class="d-flex">
									<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" required name="thongTinSearch">
									<button class="btn btn-outline-success" type="submit">Search</button>
								</div>
							</div>

						</div>
						<div>
							<% 
        			ArrayList<BookObject> booksearch = (ArrayList<BookObject>) session.getAttribute("booksearch");
                    ArrayList<BookObject> bookdisplay = (ArrayList<BookObject>) request.getAttribute("books");
                    String name = (String) session.getAttribute("thongTinSearch");
                    if(booksearch != null){
                    	if(booksearch != null && booksearch.size() == 0){
    						%>
							<p style="color:red">Không có sách có tên là <%= name %></p>
							<%
    					}else{
    						%>
							<p style="color:green">Có <%= booksearch.size() %> sách có tên là <%= name %></p>
							<%
    					}
                    }
					
        			%>
						</div>
					</form>
				</div>

				<div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy-example" tabindex="0">
					<h4 id="#scrolllistbook">Danh sách cuốn sách</h4>
					<a href="bookaddcontrol"
							class="btn btn-primary mb-3 text-white">Thêm mới</a>
					<div class="scrollable-frame">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Actions</th>
									<th scope="col">STT</th>
									<th scope="col">Name</th>
									<th scope="col">Publishing year</th>
									<th scope="col">Image</th>
									<th scope="col">Description</th>
									<th scope="col">Price</th>
									<th scope="col">Inventory number</th>
									<th scope="col">Pape number</th>
									<th scope="col">Status</th>
									<th scope="col">Language</th>
									<th scope="col">Author</th>
									<th scope="col">Category</th>
									<th scope="col">Publisher</th>
								</tr>
							</thead>
							<tbody>
								<%
				Category category = new Category();
				Publisher publisher = new Publisher();
				Author author = new Author();
                 ArrayList<BookObject> books = new ArrayList<BookObject>();
                if(booksearch != null){
                	books.addAll(booksearch);
                }else{
                	books.addAll(bookdisplay);
                }
                int i = 1;%>
								<% for(BookObject book : books){ %>
								<tr class="tr_center">
									<td class="d-flex">
										<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal<%= book.getBook_id() %>">Remove</button>
										<!-- Modal -->
										<div class="modal fade" id="exampleModal<%= book.getBook_id() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
														<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														Do you want to remove a book with ID of <%= book.getBook_id() %> and NAME of <%= book.getBook_name() %>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
														<button type="button" class="btn btn-danger">
															<a href="bookremove?bid=<%= book.getBook_id() %>" style="color:white;text-decoration:none">Remove</a>
														</button>
													</div>
												</div>
											</div>
										</div>

										<button class="btn btn-success ">
											<a href="bookedit?bid=<%= book.getBook_id() %>" style="color:white;text-decoration:none">
												Edit
											</a>
										</button>
									</td>
									<td><%= i %></td>
									<td><%= book.getBook_name() %></td>
									<td><%= book.getBook_publishing_year() %></td>
									<td><img src="ImageDisplayServlet?imageName=<%= book.getBook_image() %>" style="width: 70px;height: 70px" alt="<%= book.getBook_image() %>"></td>
									<td><%= book.getBook_description() %></td>
									<td><%= book.getBook_price() %></td>
									<td><%= book.getBook_inventory_number() %></td>
									<td><%= book.getBook_page_number() %></td>
									<td><%= book.getBook_status() %></td>
									<td><%= book.getBook_language() %></td>
									<td><%= author.searchBook(book.getAuthor_id()).getAuthor_name() %></td>
									<td><%= category.searchCategory(book.getCategory_id()).getCategory_name() %></td>
									<td><%= publisher.searchPublisher(book.getPublisher_id()).getPublisher_name() %></td>
								</tr>
								<% i= i + 1;}; session.removeAttribute("booksearch"); %>
							</tbody>
						</table>
					</div>

			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>
</body>
</html>