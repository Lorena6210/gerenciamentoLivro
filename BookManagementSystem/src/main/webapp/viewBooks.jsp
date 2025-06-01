<%@ page import="java.util.*, com.example.model.Book" %>
<html>
<head><title>Books</title></head>
<body>
    <h2>Books List</h2>
    <table border="1">
        <tr><th>ID</th><th>Title</th><th>Author</th><th>Year</th></tr>
        <%
            List<Book> books = (List<Book>) request.getAttribute("bookList");
            for (Book book : books) {
        %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getYear() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
