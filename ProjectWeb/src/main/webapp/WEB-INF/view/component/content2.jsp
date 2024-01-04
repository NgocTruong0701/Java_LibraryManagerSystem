<%@page import="it602003.objects.BookObject"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="it602003.objects.CategoryObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.process.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    ArrayList<CategoryObject> categorys = (ArrayList<CategoryObject>)request.getAttribute("categorys_trang_chu"); 
    ArrayList<BookObject> books = (ArrayList<BookObject>)request.getAttribute("list_book_content2");
    int currentPage = (int) request.getAttribute("currentPage");
    int totalPages = (int)request.getAttribute("totalPages");
%>
<!-- Category and Content Sections -->
<div class="category-section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 text-center card">
                <p class="category-name card-title">Chuyên Mục</p>
                <ul class="list-group list-group-flush">
                    <% 
                        for(CategoryObject category : categorys){
                    %>
                    <li class="list-group-item"><a  href="trangchu?cid=<%= category.getCategory_id() %>"><%= category.getCategory_name() %><span class="text-danger"> (<%= category.getCategory_total_book() %>)</span></a></li>
                    <% 
                        }
                    %>
                </ul>
            </div>
            <div class=" col-md-8 card ">
                <p class="category-name card-title">${name_category}</p>
                <!-- Phần hiển thị danh sách sách -->
                <div class="card-body ">
                    <div class="row mb-4">
                        <% 
                            for (BookObject book : books) {
                        %>
                        <div class="card col-md-4 col-sm-6 m-1" style="width: 13rem;height: 320px;">
                            <img src="ImageDisplayServlet?imageName=<%= book.getBook_image() %>" class="card-img-top" style="width: 100%;height: 70%;object-fit: cover;" alt="...">
                            <div class="card-footer">
                                <p class="card-text" style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
                                    <a href="trangmuon?bid=<%= book.getBook_id() %>" class="card-link fs-6"><%= book.getBook_name() %></a>
                                </p>
                                <span class="text-danger">Giá: <%= book.getBook_price() %></span>
                                <br>
                                <span class="text-success">Số sách rỗi: <%= book.getBook_inventory_number() %></span>
                            </div>
                        </div>
                        <% 
                            }
                        %>
                    </div>
                </div>
                <!-- Phân trang-->
                <!-- Phân trang -->
				<!-- Phân trang -->
<div class="card-footer" id="card_phantrang">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <% 
                for (int i = 1; i <= totalPages; i++) {
            %>
            <li class="page-item <% if (i == currentPage) out.print("active"); %>">
                <a class="page-link" href="trangchu?cid=<%= request.getParameter("cid") %>&page=<%= i %>"><%= i %></a>
            </li>
            <% 
                }
            %>
        </ul>
    </nav>
</div>
				
            </div>
        </div>
    </div>
</div>
