<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.Blog" %>
<%@ page import="com.example.demo1.DBManage" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
  <%@include file="nav.jsp"%>

  <%
    List<Blog> blogs = DBManage.getAllBlog();
    for (Blog blog:blogs){
  %>

  <div class="card w-75">
    <div class="card-body">
      <h5 class="card-title"><%=blog.getTitle()%></h5>
      <p class="card-text"><%=blog.getContent()%></p>
      <p class="card-text">Posted <strong><%=blog.getUser().getFullName()%></strong></p>
      <p>At <strong><%=blog.getPostDate()%></strong></p>

      <a href="/blogDetails?id=<%=blog.getId()%>" class="btn btn-success">Details</a>
    </div>
  </div>
  <%
    }
  %>

</div>
</body>
</html>