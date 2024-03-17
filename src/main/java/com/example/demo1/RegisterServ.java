package com.example.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/regist")
public class RegisterServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailsign");
        String password = req.getParameter("passwordsign");
        String repeatpass = req.getParameter("repaet");
        String fullname = req.getParameter("fullname");

        User user = DBManage.getUser(email);
        if (user == null){
            if (password.equals(repeatpass)){
                User user1 = new User();
                user1.setEmail(email);
                user1.setPassword(password);
                user1.setFullName(fullname);
                DBManage.registUser(user1);
                resp.sendRedirect("/home");
            }
        }
        else
            resp.sendRedirect("/regist?busy");
    }
}
