<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="in.ineuron.dto.StudentDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Student Requests</title>
<style>
    table { border-collapse: collapse; width: 80%; margin: 20px auto; }
    th, td { border: 1px solid #333; padding: 8px; text-align: center; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; color: white; padding: 5px 10px; border-radius: 3px; }
    .approve { background-color: green; }
    .reject { background-color: red; }
</style>
</head>
<body>
<h2 style="text-align:center;">Pending Student Requests</h2>

<%
    List<StudentDto> pendingRequests = (List<StudentDto>) request.getAttribute("pendingRequests");
%>

<table>
    <tr>
        <th>Student ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Course</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <%
        if (pendingRequests != null && !pendingRequests.isEmpty()) {
            for (StudentDto s : pendingRequests) {
    %>
    <tr>
        <td><%= s.getStudentId() %></td>
        <td><%= s.getStudentName() %></td>
        <td><%= s.getStudentEmail() %></td>
        <td><%= s.getStudentCourse() %></td>
        <td><%= s.getStatus() %></td>
        <td>
            <a href="<%=request.getContextPath()%>/AdminController/approveStudent?studentId=<%=s.getStudentId()%>" class="approve">Approve</a>
            <a href="<%=request.getContextPath()%>/AdminController/rejectStudent?studentId=<%=s.getStudentId()%>" class="reject">Reject</a>
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="6">No pending requests.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
