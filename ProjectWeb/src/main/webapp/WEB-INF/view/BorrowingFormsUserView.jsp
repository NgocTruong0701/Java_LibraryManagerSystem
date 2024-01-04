<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
	<%@ include file="component/borrowingFormCompo.jsp" %>
	<%@ include file="component/footer_trangchu.jsp" %>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- Bootstrap Icons -->
</html>
