package com.example.demo1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(value = "/login")
public class LogServ extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailsign");
        String password = req.getParameter("passwordsign");

        HttpSession httpSession = req.getSession();

        User user = DBManage.getUser(email);
        if (user!= null && user.getPassword().equals(password)){
            resp.sendRedirect("/profile");
            httpSession.setAttribute("ONLINE_USER",user);
        }
        else
            resp.sendRedirect("/login?error");
    }
}
