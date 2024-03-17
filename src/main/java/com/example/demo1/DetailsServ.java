package com.example.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/blogDetails")
public class DetailsServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Blog blog = DBManage.getBlogById(Long.valueOf(id));
        req.setAttribute("blogDetails",blog);
        req.getRequestDispatcher("details.jsp").forward(req,resp);
    }
}
