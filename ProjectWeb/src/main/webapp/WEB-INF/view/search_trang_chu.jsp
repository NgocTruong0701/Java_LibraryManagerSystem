<%@page import="it602003.objects.BookObject"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="it602003.objects.CategoryObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it602003.process.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Integer sizeSearchResults = (Integer) request.getAttribute("sizeSearchResults");
    ArrayList<BookObject> books = (ArrayList<BookObject>)request.getAttribute("searchResults");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset='utf-8'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.17.0/dist/css/bootstrap-icons.min.css">
    <style>
        .search-input {
            border: none;
            border-radius: 0;
            border-bottom: 1px solid #ced4da;
        }

        .search-button {
            border: none;
            border-radius: 0;
        }

        #select_search {
            width: 30%;
        }

        .category-section {
            padding: 20px 0;
            background-color: #f8f9fa;
        }

        .category-name {
            font-size: 1.5rem;
            font-weight: bold;
        }

        .content-section {
            padding: 20px 0;
        }
        #card_phantrang{margin: 0 auto;}
    </style>
</head>
<body>
<%@ include file="component/header_trangchu.jsp" %>

<!-- Category and Content Sections -->
<div class="category-section">
    <div class="container">
        <div class="row">
            <div class="col-md-4 text-center card">
                <p class="category-name card-title">Kết quả tìm kiếm</p>
            </div>
            <div class=" col-md-8 card ">
                <p class="category-name card-title">Tổng có <%= sizeSearchResults %> cuốn sách được tìn thấy</p>
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
            </div>
        </div>
    </div>
</div>

<%@ include file="component/footer_trangchu.jsp" %>	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>