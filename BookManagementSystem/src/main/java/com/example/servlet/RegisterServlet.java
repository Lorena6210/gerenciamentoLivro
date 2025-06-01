package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userDAO.addUser (user);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
