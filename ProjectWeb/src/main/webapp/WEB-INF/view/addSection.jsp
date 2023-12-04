<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Section</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <!-- Include any additional CSS -->
</head>
<body>

<div class="container-lg">
    <div class="card my-4">
        <div class="card-header text-bg-primary"></div>
        <div class="card-body">
            <h1>Thêm mới Section</h1>
            <!-- Form to add new section -->
            <form action="${pageContext.request.contextPath}/section/addsection" method="post">
                <!-- Input fields for adding section -->
                <div class="mb-3">
                    <label for="sectionName" class="form-label">Tên chuyên mục:</label>
                    <input type="text" class="form-control" id="sectionName" name="sectionName">
                </div>
                <div class="mb-3">
                    <label for="sectionNotes" class="form-label">Ghi chú:</label>
                    <textarea class="form-control" id="sectionNotes" name="sectionNotes"></textarea>
                </div>
                <!-- Submit button to add section -->
                <button type="submit" class="btn btn-primary" >Thêm mới</button>
            </form>
        </div>
        <div class="card-footer text-bg-info"></div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<!-- Include any additional JS -->
</body>
</html>
