package com.example.dao;

import com.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, isAvailable) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.executeUpdate();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("isAvailable")));
            }
        }
        return books;
    }

    public void updateBookAvailability(int bookId, boolean isAvailable) throws SQLException {
        String sql = "UPDATE books SET isAvailable = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, isAvailable);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        }
    }
}

