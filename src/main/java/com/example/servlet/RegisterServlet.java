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

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Check if user already exists
            if (userDAO.findByEmail(email) != null) {
                request.setAttribute("error", "Email already registered");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Generate TOTP secret key
            String secretKey = Utils.generateSecretKey();

            // Save user
            User user = new User(email, password, secretKey);
            userDAO.saveUser(user);

            // Generate QR code data
            String companyName = "My Awesome Company";
            String barCodeData = Utils.getGoogleAuthenticatorBarCode(secretKey, email, companyName);

            // Store data in session for QR code display
            request.getSession().setAttribute("barCodeData", barCodeData);
            request.getSession().setAttribute("secretKey", secretKey);

            response.sendRedirect("qrcode.jsp");

        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
