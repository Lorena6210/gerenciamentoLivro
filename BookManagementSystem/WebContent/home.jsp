<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    User