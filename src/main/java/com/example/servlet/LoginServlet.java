package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String totpCode = request.getParameter("totpCode");

        try {
            User user = userDAO.findByEmail(email);
            if (user != null && userDAO.verifyPassword(password, user.getPassword())) {
                // Verify TOTP code
                if (totpCode.equals(Utils.getTOTPCode(user.getSecretKey()))) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("dashboard.jsp");
                } else {
                    request.setAttribute("error", "Invalid 2FA code");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}