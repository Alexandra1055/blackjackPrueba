package org.example.blackjack.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//seria esto o sino en GameServlet meter a pelo el usuario y password si no pide que creemos login
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("user", "admin"); // login automático

        // Forward a la JSP real en la raíz del webapp
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");

        if (user == null || !"admin".equals(user)) {
            // usuario no autenticado
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Accés restringit");
            return;
        }
    }
}
