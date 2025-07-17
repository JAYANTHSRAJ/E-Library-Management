<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %> 
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

    /* New Table Styling */
    table {
        border-collapse: collapse;
        width: 80%;
        margin-top: 20px;
        background-color: #fff;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
        font-family: Arial, sans-serif;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
        color: #333;
        font-weight: bold;
    }

    tr:hover {
        background-color: #f9f9f9;
    }
</style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" style="background-repeat: no-repeat; background-size: cover;">
    <header>
        <div class="logo"><img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px"></div>
        <div class="text">
            <h1> E-Library </h1> 
            <span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/adminoperations.html" style="font-weight: normal; text-decoration: none; color: inherit;">Home</a></span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/about.html" style="font-weight: normal; text-decoration: none; color: inherit;">About</a></span>
                <span><a href="<%= request.getContextPath() %>/contactus.html" style="font-weight: normal; text-decoration: none; color: inherit;">Contact Us</a></span>
            </span>
        </div>
    </header>
    <br/><br/><br/><br/>
    <c:choose>
        <c:when test="${student ne null || !empty student}">
            <center>
                <table>
                    <tr>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Student Department</th>
                        <th>Student Year</th>
                        <th>Student Course</th>
                        <th>Student Phone no</th>
                        <th>Student Email-ID</th>
                    </tr>
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>${student.studentDepartment}</td>
                        <td>${student.studentYear}</td>
                        <td>${student.studentCourse}</td>
                        <td>${student.studentPhoneno}</td>
                        <td>${student.studentEmail}</td>
                    </tr>
                </table>
            </center>
        </c:when>
        <c:otherwise>
            <h1 style="color:red;text-align:center">No Record Available</h1>
            <center><a href="<%= request.getContextPath() %>/searchstudent.html">BACK</a></center>
        </c:otherwise>
    </c:choose>
</body>
</html>
