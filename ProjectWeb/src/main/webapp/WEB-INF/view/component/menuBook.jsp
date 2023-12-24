<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav id="navbar-example2" class="navbar navbar-light bg-light px-3  ">
  <ul class="nav nav-pills">
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Book</a>
      <ul class="dropdown-menu">
      	<li><a class="dropdown-item" href="bookview">List Book</a></li>
        	<li><a class="dropdown-item" href="bookaddcontrol">Add New Book</a></li>
      </ul>
    </li>
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Category</a>
      <ul class="dropdown-menu">
      	<li><a class="dropdown-item" href="categoryview">List Category</a></li>
        	<li><a class="dropdown-item" href="categoryadd">Add New Category</a></li>
      </ul>
    </li>
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">Publisher</a>
      <ul class="dropdown-menu">
      	<li><a class="dropdown-item" href="publisherview">List Publisher</a></li>
        	<li><a class="dropdown-item" href="publisheradd">Add New Publisher</a></li>
      </ul>
    </li>
    
  </ul>
</nav>