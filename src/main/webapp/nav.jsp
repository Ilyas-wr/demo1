<%@ page import="com.example.demo1.User" %>
<%
    User user = (User) request.getSession().getAttribute("ONLINE_USER");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">

                <%
                    if (user == null){


                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/regist">Logup</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/login">Login</a>
                </li>

                <% }%>

                <%
                    if (user!=null){
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/login"><%=user.getFullName()%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/addpage">Add Blog</a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>

