package com.example.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/addcomm")
public class AddComment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comm");
        String blogid = req.getParameter("blogId");
        User user = (User) req.getSession().getAttribute("ONLINE_USER");
        Blog blog = DBManage.getBlogById(Long.valueOf(blogid));
        Comments comments = new Comments();
        comments.setBlog(blog);
        comments.setUser(user);
        comments.setComment(comment);
        DBManage.addComm(comments);
        resp.sendRedirect("/home");
    }
}
