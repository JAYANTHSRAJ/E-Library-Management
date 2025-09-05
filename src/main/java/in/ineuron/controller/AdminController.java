package in.ineuron.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.AdminDto;
import in.ineuron.dto.StudentDto;
import in.ineuron.service.IAdminService;
import in.ineuron.servicefactory.AdminServiceFactory;
import in.ineuron.util.EmailUtil;
import in.ineuron.util.JdbcUtil;

@WebServlet("/AdminController/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IAdminService adminService = AdminServiceFactory.getadminservice();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.endsWith("AdminLogin")) {
            handleLogin(request, response);

        } else if (uri.endsWith("registeradmin")) {
            handleRegistration(request, response);

        } else if (uri.endsWith("viewPendingRequests")) {
            handleViewPendingRequests(request, response);

        } else if (uri.endsWith("approveStudent")) {
            handleApproveStudent(request, response);

        } else if (uri.endsWith("rejectStudent")) {
            handleRejectStudent(request, response);
        }
    }

    // ----------------- LOGIN -----------------
    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminId = request.getParameter("admin_Id");
        String adminEmail = request.getParameter("admin_email");
        String adminPassword = request.getParameter("admin_password");

        if (adminId == null || adminEmail == null || adminPassword == null ||
                adminId.trim().isEmpty() || adminEmail.trim().isEmpty() || adminPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All login fields are required.");
            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
            return;
        }

        try (Connection connection = JdbcUtil.getJdbcConnection()) {
            if (connection == null) {
                request.setAttribute("errorMessage", "Database connection error.");
                request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                return;
            }

            String sqlQuery = "SELECT adminEmail, adminPassword FROM admindb WHERE adminId = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
                pstmt.setInt(1, Integer.parseInt(adminId.trim()));
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedEmail = rs.getString("adminEmail");
                        String storedPassword = rs.getString("adminPassword");

                        if (adminEmail.trim().equalsIgnoreCase(storedEmail.trim()) &&
                                adminPassword.trim().equals(storedPassword.trim())) {
                            response.sendRedirect("../adminoperations.html");
                        } else {
                            request.setAttribute("errorMessage", "Invalid email or password.");
                            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Admin ID not found.");
                        request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Internal server error during login.");
            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
        }
    }

    // ----------------- REGISTRATION -----------------
    private void handleRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AdminDto admin = new AdminDto();
            String adminIdStr = request.getParameter("admin_id");

            if (adminIdStr != null && !adminIdStr.trim().isEmpty()) {
                admin.setAdminId(Integer.parseInt(adminIdStr));
            } else {
                request.setAttribute("errorMessage", "Admin ID cannot be empty");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                return;
            }

            admin.setAdminName(request.getParameter("admin_name"));
            admin.setAdminEmail(request.getParameter("admin_email"));
            admin.setAdminPassword(request.getParameter("admin_password"));

            String status = adminService.registeradmin(admin);

            request.setAttribute("status", "success".equals(status) ? "success" : "failed");
            request.getRequestDispatcher("../registeradminstatus.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("../registeradminstatus.jsp").forward(request, response);
        }
    }

    // ----------------- VIEW PENDING STUDENT REQUESTS -----------------
    private void handleViewPendingRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<StudentDto> pendingRequests = new ArrayList<>();
        String sql = "SELECT * FROM student_requests WHERE status = 'PENDING'";
        try (Connection con = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StudentDto dto = new StudentDto();
                dto.setStudentId(rs.getString("student_id"));
                dto.setStudentName(rs.getString("student_name"));
                dto.setStudentEmail(rs.getString("student_email"));
                dto.setStudentCourse(rs.getString("student_course"));
                dto.setStatus(rs.getString("status"));
                pendingRequests.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("pendingRequests", pendingRequests);
        RequestDispatcher rd = request.getRequestDispatcher("../viewPendingRequests.jsp");
        rd.forward(request, response);
    }

    // ----------------- APPROVE STUDENT -----------------
    private void handleApproveStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        if (studentId != null) {
            try (Connection con = JdbcUtil.getJdbcConnection()) {

                // Get student details
                String sqlSelect = "SELECT * FROM student_requests WHERE student_id = ?";
                String studentEmail = "", studentName = "", studentCourse = "";
                try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
                    psSelect.setString(1, studentId);
                    try (ResultSet rs = psSelect.executeQuery()) {
                        if (rs.next()) {
                            studentEmail = rs.getString("student_email");
                            studentName = rs.getString("student_name");
                            studentCourse = rs.getString("student_course");
                        }
                    }
                }

                // Copy student to studentdb including all columns
                String sqlInsert = "INSERT INTO studentdb "
                        + "(studentId, studentName, studentDepartment, studentCourse, studentYear, studentPhoneno, studentEmailId, studentPassword, status) "
                        + "SELECT CAST(student_id AS UNSIGNED), student_name, student_department, student_course, CAST(student_year AS UNSIGNED), "
                        + "student_phone, student_email, student_password, 'APPROVED' "
                        + "FROM student_requests WHERE student_id = ?";
                try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                    psInsert.setString(1, studentId);
                    psInsert.executeUpdate();
                }

                // Delete from student_requests
                String sqlDelete = "DELETE FROM student_requests WHERE student_id = ?";
                try (PreparedStatement psDelete = con.prepareStatement(sqlDelete)) {
                    psDelete.setString(1, studentId);
                    psDelete.executeUpdate();
                }

                // Send approval email
                String subject = "Library Request Approved";
                String body = "Hello " + studentName + ",\n\n"
                            + "Your library request for course " + studentCourse + " has been APPROVED.\n"
                            + "You can now access the library system.\n\nRegards,\nLibrary Team";
                EmailUtil.sendEmail(studentEmail, subject, body);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/AdminController/viewPendingRequests");
    }

    // ----------------- REJECT STUDENT -----------------
    private void handleRejectStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        if (studentId != null) {
            try (Connection con = JdbcUtil.getJdbcConnection()) {

                // Get student details
                String sqlSelect = "SELECT * FROM student_requests WHERE student_id = ?";
                String studentEmail = "", studentName = "", studentCourse = "";
                try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
                    psSelect.setString(1, studentId);
                    try (ResultSet rs = psSelect.executeQuery()) {
                        if (rs.next()) {
                            studentEmail = rs.getString("student_email");
                            studentName = rs.getString("student_name");
                            studentCourse = rs.getString("student_course");
                        }
                    }
                }

                // Update status to REJECTED
                String sqlDelete = "DELETE FROM student_requests WHERE student_id = ?";

                try (PreparedStatement psUpdate = con.prepareStatement(sqlDelete)) {
                    psUpdate.setString(1, studentId);
                    psUpdate.executeUpdate();
                }

                // Send rejection email
                String subject = "Library Request Rejected";
                String body = "Hello " + studentName + ",\n\n"
                            + "Your library request for course " + studentCourse + " has been REJECTED.\n"
                            + "For more details, please contact the library.\n\nRegards,\nLibrary Team";
                EmailUtil.sendEmail(studentEmail, subject, body);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/AdminController/viewPendingRequests");
    }
}
