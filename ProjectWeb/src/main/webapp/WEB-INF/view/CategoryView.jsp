



<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it602003.objects.CategoryObject" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 5 Layout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
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
				
				<!-- Search  -->
    <div class="container-fluid border border-primary p-3 mb-3">
        <h4 id="#scrollsearchcategory">Search Category</h4>
        <form action="categorysearch" class="needs-validation">
            <div class="row">
                <div class="col-3">
                    <select class="form-select" aria-label="Default select example" name="luaChonSearch">
                        <option value="1" selected>ID</option>
                        <option value="2">NAME</option>
                        <option value="3">AUTHOR</option>
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
                ArrayList<CategoryObject> categorysearch = (ArrayList<CategoryObject>) session.getAttribute("categorysearch");
                ArrayList<CategoryObject> categorydisplay = (ArrayList<CategoryObject>) request.getAttribute("categorys");
                String name = (String) session.getAttribute("thongTinSearch");
                if(categorysearch != null){
                    if(categorysearch.size() == 0){
                        %>
                        <p style="color:red">Không có danh mục có tên là <%= name %></p>
                        <%
                    }else{
                        %>
                        <p style="color:green">Có <%= categorysearch.size() %> danh mục có tên là <%= name %></p>
                        <%
                    }
                }
                %>
            </div>
        </form>                            
    </div>
      
    <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy-example" tabindex="0">
        <h4 id="#scrolllistcategory">Danh sách danh mục sách</h4>
        <a href="categoryadd"
							class="btn btn-primary mb-3 text-white">Thêm mới</a>
        <div class="scrollable-frame">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Actions</th>
                        <th scope="col">STT</th>
                        <th scope="col">Tên danh mục</th>
                        <th scope="col">Tổng lượng sách</th>
                        <!-- Add more columns as needed -->
                    </tr>
                </thead>
                <tbody>
                    <% 
                     ArrayList<CategoryObject> categorys = new ArrayList<CategoryObject>();
                    if(categorysearch != null){
                        categorys.addAll(categorysearch);
                    }else{
                        categorys.addAll(categorydisplay);
                    }
                    int i = 1;%>
                    <% for(CategoryObject category : categorys){ %>
                        <tr class="tr_center">
                            <td class="d-flex">
                              <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal<%= category.getCategory_id() %>">Remove</button>
                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal<%= category.getCategory_id() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                  <div class="modal-dialog">
                                    <div class="modal-content">
                                      <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                      </div>
                                      <div class="modal-body">
                                        Do you want to remove a category with ID of <%= category.getCategory_id() %> and NAME of <%= category.getCategory_id() %>
                                      </div>
                                      <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-danger">
                                            <a href="categoryremove?cid=<%= category.getCategory_id() %>" style="color:white;text-decoration:none">Remove</a>
                                        </button>
                                      </div>
                                    </div>
                                  </div>
                                </div>
         
                            <button class="btn btn-success ">
                                <a href="categoryedit?cid=<%= category.getCategory_id() %>"  style="color:white;text-decoration:none">
                                    Edit
                                </a>
                            </button>
                        </td>
                        <th><%= i %></th>
                        <td><%= category.getCategory_name() %></td>
                        <td><%= category.getCategory_total_book() %></td>
                    </tr>
                    <% i= i + 1;}; session.removeAttribute("categorysearch"); %>            
                </tbody>
            </table>
        </div>
    </div>
				
			</main>
		</div>
		<!-- Import footer -->
		<jsp:include page="component/footer.jsp" />
	</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    // Hàm vẽ biểu đồ
    function drawChart() {
        var categoryData = [
            <% for (CategoryObject category : categorys) { %>
                { label: '<%= category.getCategory_name() %>', value: <%= category.getCategory_total_book() %> },
            <% } %>
        ];

        var ctx = document.getElementById('bookChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: categoryData.map(item => item.label),
                datasets: [{
                    label: 'Number of Books',
                    data: categoryData.map(item => item.value),
                    backgroundColor: 'rgba(10, 10, 235, 0.8)',
                    borderColor: 'rgba(75, 192, 192, 1)',
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
        drawChart();
    });
</script>
</body>
</html>