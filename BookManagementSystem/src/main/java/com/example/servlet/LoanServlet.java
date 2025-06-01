package com.example.servlet;

import com.example.dao.BookDAO;
import com.example.dao.LoanDAO;
import com.example.model.Loan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/loans")
public class LoanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User ) session.getAttribute("user");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            LoanDAO loanDAO = new LoanDAO(connection);
            Loan loan = new Loan();
            loan.setUser Id(user.getId());
            loan.setBookId(bookId);
            loan.setLoanDate(new Date());
            loanDAO.addLoan(loan);

            BookDAO bookDAO = new BookDAO(connection);
            bookDAO.updateBookAvailability(bookId, false); // Mark book as not available
            response.sendRedirect("loans.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
