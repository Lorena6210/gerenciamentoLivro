package com.example.servlet;

import com.example.dao.BookDAO;
import com.example.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            BookDAO bookDAO = new BookDAO(connection);
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("books.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        boolean isAvailable = true;

        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            BookDAO bookDAO = new BookDAO(connection);
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setAvailable(isAvailable);
            bookDAO.addBook(book);
            response.sendRedirect("books");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
