<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Details</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/student_style.css">
<style>
    header {
        background-color: transparent; 
        padding: 10px 0;
        color: #000;
    }
    .logo {
        display: inline-block;
    }
    .text {
        display: inline-block;
        margin-left: 20px;
    }
    .dummies {
        float: right;
    }
    .dummies ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .dummies li {
        display: inline;
        margin-left: 20px;
    }
    .dummies a {
        text-decoration: none;
        color: #000; 
    }
    .operations {
        position: absolute;
        padding-left: 350px;
    }
    .operations select {
        height: 35px;
        width: 200px;
    }
    .btn {
        border: 2px solid grey;
        border-radius: 29px;
        margin: 7px auto;
        width: 86px;
        font-size: 15px;
        padding: 10px;
        background: none;
        font-family: 'Ubuntu', sans-serif;
        outline: none;
    }
    .btn:hover {
        cursor: pointer;
        background-color: burlywood;
    }

    /* 🔵 TABLE STYLING ADDED HERE */
    table {
        border-collapse: collapse;
        margin: 30px auto;
        width: 80%;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
        background-color: #ffffffd9;
        font-family: Arial, sans-serif;
        border-radius: 8px;
        overflow: hidden;
    }

    th {
        background-color: #3498db;
        color: white;
        padding: 12px;
        font-size: 16px;
    }

    td {
        padding: 10px;
        font-size: 15px;
        color: #333;
        text-align: center;
    }

    tr:nth-child(even) {
        background-color: #f2f9ff;
    }

    tr:nth-child(odd) {
        background-color: #ffffff;
    }

    tr:hover {
        background-color: #d3eaf7;
        transition: background-color 0.3s ease;
    }
</style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" style="background-repeat: no-repeat; background-size: cover;">
    <header>
        <div class="logo"><img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px"></div>
        <div class="text">
            <h1> e-Library </h1> 
            <span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/student_operations.html" style="font-weight: normal; text-decoration: none; color: inherit;">Home</a></span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/about.html" style="font-weight: normal; text-decoration: none; color: inherit;">About</a></span>
                <span><a href="<%= request.getContextPath() %>/contactus.html" style="font-weight: normal; text-decoration: none; color: inherit;">Contact Us</a></span>
            </span>
        </div>
    </header>
    <br><br>

    <center>
        <c:choose>
            <c:when test="${not empty issuedList}">
                <h2>Currently Issued Books:</h2>
                <table border="1" cellpadding="5" cellspacing="0">
                    <tr>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Issue Date</th>
                        <th>Due Date</th>
                    </tr>
                    <c:forEach var="book" items="${issuedList}">
                        <tr>
                            <td>${book.bookId}</td>
                            <td>${book.bookName}</td>
                            <td>${book.bookIssueDate}</td>
                            <td>${book.bookIssueDueDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2 style="color:red;">No issued books found for this student.</h2>
            </c:otherwise>
        </c:choose>
    </center>

    <center>
        <a href="${pageContext.request.contextPath}/student_operations.html">Back</a>
    </center>
</body>
</html>
