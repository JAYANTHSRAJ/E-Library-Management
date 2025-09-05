package in.ineuron.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.StudentDto;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;
<<<<<<< HEAD
import in.ineuron.util.EmailUtil;
=======
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
import in.ineuron.util.JdbcUtil;

@WebServlet("/StudentController/*")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

<<<<<<< HEAD
=======
    
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

<<<<<<< HEAD
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request URI: " + request.getRequestURI());

=======

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request URI: " + request.getRequestURI());
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        StudentDto student = null;
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

<<<<<<< HEAD
        try {
            String uri = request.getRequestURI();

            // ====================== Student Login =========================
            if (uri.endsWith("studentlogin")) {
                connection = JdbcUtil.getJdbcConnection();
                String sqlSelectQuery = "SELECT studentId, studentEmailId, studentPassword, status FROM studentdb WHERE studentId=?";
                pstmt = connection.prepareStatement(sqlSelectQuery);
                String studentIdParam = request.getParameter("student_id");

=======
        
        if (request.getRequestURI().endsWith("studentlogin")) {
            try {
                connection = JdbcUtil.getJdbcConnection();
                String sqlSelectQuery = "SELECT studentId, studentEmailId, studentPassword FROM studentdb WHERE studentId=?";
                pstmt = connection.prepareStatement(sqlSelectQuery);
                String studentIdParam = request.getParameter("student_id");
                
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                if (studentIdParam != null && !studentIdParam.trim().isEmpty()) {
                    pstmt.setInt(1, Integer.parseInt(studentIdParam));
                } else {
                    request.setAttribute("error", "Please enter Student ID.");
                    request.getRequestDispatcher("../studentVerificationFail.jsp").forward(request, response);
                    return;
                }

                rs = pstmt.executeQuery();
                RequestDispatcher rd = null;

<<<<<<< HEAD
                if (rs != null && rs.next()) {
                    String semail = rs.getString("studentEmailId");
                    String spassword = rs.getString("studentPassword");
                    String status = rs.getString("status");

                    if (semail.equals(request.getParameter("student_email")) &&
                            spassword.equals(request.getParameter("student_password")) &&
                            "APPROVED".equalsIgnoreCase(status)) {

                        System.out.println("Valid Credentials for Student ID: " + studentIdParam);
                        request.getSession().setAttribute("studentId", rs.getInt("studentId"));
                        response.sendRedirect(request.getContextPath() + "/student_operations.html");

                    } else {
                        String errorMsg = "Invalid Email or Password.";
                        if (!"APPROVED".equalsIgnoreCase(status)) {
                            errorMsg = "Your account is not approved yet.";
                        }
                        request.setAttribute("error", errorMsg);
=======
                // If student exists in the database
                if (rs != null && rs.next()) {
                    String semail = rs.getString("studentEmailId");
                    String spassword = rs.getString("studentPassword");

                    // If the email and password match
                    if (semail.equals(request.getParameter("student_email"))
                            && spassword.equals(request.getParameter("student_password"))) {
                        
                        System.out.println("Valid Credentials for Student ID: " + studentIdParam);

                        // Use redirect instead of forward for static HTML
                        response.sendRedirect(request.getContextPath() + "/student_operations.html");
                        
                    } else {
                        System.out.println("Invalid Email or Password for Student ID: " + studentIdParam);
                        request.setAttribute("error", "Invalid Email or Password.");
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                        rd = request.getRequestDispatcher("../studentVerificationFail.jsp");
                        rd.forward(request, response);
                    }
                } else {
<<<<<<< HEAD
=======
                    // Student ID not found in the database
                    System.out.println("Invalid Student ID: " + studentIdParam);
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                    request.setAttribute("error", "Invalid Student ID.");
                    rd = request.getRequestDispatcher("../studentVerificationFail.jsp");
                    rd.forward(request, response);
                }
<<<<<<< HEAD
            }

           
        
         // ====================== Forgot Password ======================
            else if (uri.endsWith("forgotpassword")) {
                String studentIdParam = request.getParameter("student_id");
                String email = request.getParameter("student_email");

                if (studentIdParam == null || studentIdParam.trim().isEmpty() ||
                    email == null || email.trim().isEmpty()) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "Please enter both Student ID and Email.");
                    request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
                    return;
                }

                try (Connection con = JdbcUtil.getJdbcConnection()) {
                    String sql = "SELECT studentPassword, studentName FROM studentdb WHERE studentId = ? AND studentEmailId = ?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setInt(1, Integer.parseInt(studentIdParam));
                        ps.setString(2, email);

                        try (ResultSet rs2 = ps.executeQuery()) {
                            if (rs2.next()) {
                                String password = rs2.getString("studentPassword");
                                String studentName = rs2.getString("studentName");

                                // Send password to email
                                String subject = "Library Management - Forgot Password";
                                String body = "Hello " + studentName + ",\n\n"
                                            + "As requested, here is your account password: " + password + "\n\n"
                                            + "For security reasons, please change your password after login.\n\n"
                                            + "Regards,\nLibrary Team";

                                EmailUtil.sendEmail(email, subject, body);

                                request.setAttribute("status", "success");
                                request.setAttribute("message", "Password has been sent to your registered email.");
                            } else {
                                request.setAttribute("status", "failure");
                                request.setAttribute("errorMessage", "Invalid Student ID or Email.");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "Database error: " + e.getMessage());
                }

                request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
            }
            
           
            // ====================== Change Password ======================
         // ====================== Change Password ======================
            else if (uri.endsWith("changepassword")) {
                Integer studentId = (Integer) request.getSession().getAttribute("studentId");
                if (studentId == null) {
                    response.sendRedirect(request.getContextPath() + "/studentLogin.html");
                    return;
                }

                String oldPassword = request.getParameter("old_password");
                String newPassword = request.getParameter("new_password");
                String confirmPassword = request.getParameter("confirm_password");

                if (oldPassword == null || newPassword == null || confirmPassword == null ||
                        oldPassword.trim().isEmpty() || newPassword.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "All fields are required.");
                    request.getRequestDispatcher("../changepassword.jsp").forward(request, response);
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "New Password and Confirm Password do not match.");
                    request.getRequestDispatcher("../changepassword.jsp").forward(request, response);
                    return;
                }

                String result = studentService.changePassword(studentId, oldPassword, newPassword);

                if ("success".equalsIgnoreCase(result)) {
                    request.setAttribute("status", "success");
                    request.setAttribute("message", "Password changed successfully. Confirmation email sent to your registered email.");

                    // ✅ Fetch email and send confirmation
                    String email = studentService.getStudentEmailById(studentId);
                    System.out.println("Fetched email for studentId " + studentId + ": " + email);

                    if (email != null) {
                        EmailUtil.sendEmail(email,
                            "Password Updated - Library Management System",
                            "Hello, your password has been successfully updated.\n\nRegards,\nLibrary Management System");
                    } else {
                        System.out.println("⚠️ No email found for studentId: " + studentId);
                    }
                } else {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", result);
                }

                request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
            }

            // ====================== Student Registration Request =========================
            else if (uri.endsWith("student_request")) {
                student = new StudentDto();
                student.setStudentId(request.getParameter("student_id"));
                student.setStudentName(request.getParameter("student_name"));
                student.setStudentDepartment(request.getParameter("student_department"));
                student.setStudentYear(Integer.parseInt(request.getParameter("student_year")));
                student.setStudentCourse(request.getParameter("student_course"));
                student.setStudentPhoneno(request.getParameter("student_phone"));
                student.setStudentEmail(request.getParameter("student_email"));
                student.setStudentPassword(request.getParameter("student_password"));
                student.setStatus("PENDING");

                String result = studentService.saveStudentRequest(student);

                if (result != null && result.toLowerCase().contains("success")) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", result);
                }

                request.getRequestDispatcher("../student_request_status.jsp").forward(request, response);
            }


            // ====================== Add Student =========================
            else if (request.getRequestURI().endsWith("addstudent")) {
                try {
                    student = new StudentDto();
                    String sid = request.getParameter("student_id");
                    String sname = request.getParameter("student_name");
                    String sdept = request.getParameter("student_department");
                    String syear = request.getParameter("student_year");
                    String scourse = request.getParameter("student_course");
                    String sphone = request.getParameter("student_phone");
                    String semail = request.getParameter("student_email");
                    String spassword = request.getParameter("student_password");

                    if (sid == null || sid.trim().isEmpty() || sname == null || sname.trim().isEmpty() ||
                            sdept == null || sdept.trim().isEmpty() || syear == null || syear.trim().isEmpty() ||
                            scourse == null || scourse.trim().isEmpty() || sphone == null || sphone.trim().isEmpty() ||
                            semail == null || semail.trim().isEmpty() || spassword == null || spassword.trim().isEmpty()) {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", "Please fill in all the fields.");
                        request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);
                        return;
                    }

                    student.setStudentId((sid));
                    student.setStudentName(sname);
                    student.setStudentDepartment(sdept);
                    student.setStudentYear(Integer.parseInt(syear));
                    student.setStudentCourse(scourse);
                    student.setStudentPhoneno(sphone);
                    student.setStudentEmail(semail);
                    student.setStudentPassword(spassword);

                    String result = studentService.addStudent(student);

                    if (result != null && result.toLowerCase().contains("success")) {
                        request.setAttribute("status", "success");
                    } else {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", result);
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "Invalid Student ID or Year format.");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "An error occurred while adding the student.");
                }

                request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);
            }

            // ====================== Delete Student =========================
            else if (request.getRequestURI().endsWith("deletestudent")) {
                try {
                    String sidToDelete = request.getParameter("student_id");
                    if (sidToDelete == null || sidToDelete.trim().isEmpty()) {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", "Please provide Student ID to delete.");
                        request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);
                        return;
                    }
                    Integer studentIdToDelete = Integer.parseInt(sidToDelete);
                    String deleteResult = studentService.deleteStudent(studentIdToDelete);

                    if ("Student deleted successfully.".equals(deleteResult)) {
                        request.setAttribute("status", "success");
                    } else {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", deleteResult);
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "Invalid Student ID format.");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "An error occurred while deleting the student.");
                }

                request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);
            }

            // ====================== Update Student =========================
            else if (request.getRequestURI().endsWith("updatestudent")) {
                try {
                    student = new StudentDto();
                    String sidToUpdate = request.getParameter("student_id");
                    String sname = request.getParameter("student_name");
                    String sdept = request.getParameter("student_department");
                    String syear = request.getParameter("student_year");
                    String scourse = request.getParameter("student_course");
                    String sphone = request.getParameter("student_phone");
                    String semail = request.getParameter("student_email");
                    String spassword = request.getParameter("student_password");

                    if (sidToUpdate == null || sidToUpdate.trim().isEmpty()) {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", "Please provide Student ID to update.");
                        request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);
                        return;
                    }

                    student.setStudentId((sidToUpdate));
                    student.setStudentName(sname);
                    student.setStudentDepartment(sdept);
                    student.setStudentYear(Integer.parseInt(syear));
                    student.setStudentCourse(scourse);
                    student.setStudentPhoneno(sphone);
                    student.setStudentEmail(semail);
                    student.setStudentPassword(spassword);

                    String updateResult = studentService.updateStudent(student);

                    if ("Student updated successfully.".equals(updateResult)) {
                        request.setAttribute("status", "success");
                    } else {
                        request.setAttribute("status", "failure");
                        request.setAttribute("errorMessage", updateResult);
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "Invalid Student ID or Year format.");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", "An error occurred while updating the student.");
                }

                request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);
            }

            // ====================== Search Student =========================
            else if (request.getRequestURI().endsWith("searchstudent")) {
                try {
                    String sidToSearch = request.getParameter("student_id");
                    if (sidToSearch == null || sidToSearch.trim().isEmpty()) {
                        request.setAttribute("error", "Please provide Student ID to search.");
                        request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
                        return;
                    }
                    Integer studentIdToSearch = Integer.parseInt(sidToSearch);
                    student = studentService.searchStudent(studentIdToSearch);
                    request.setAttribute("student", student);
                } catch (NumberFormatException e) {
                    request.setAttribute("student", null);
                    request.setAttribute("error", "Invalid Student ID format.");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("student", null);
                    request.setAttribute("error", "An error occurred while searching for the student.");
                }

                request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
            }
        
    
        
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("status", "failure");
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("../student_request_status.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("status", "failure");
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("../student_request_status.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "failure");
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("../student_request_status.jsp").forward(request, response);
        } finally {
            JdbcUtil.cleanUp(connection, pstmt, rs);
=======
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "An SQL error occurred during login.");
                request.getRequestDispatcher("../studentVerificationFail.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "An unexpected error occurred during login.");
                request.getRequestDispatcher("../studentVerificationFail.jsp").forward(request, response);
            } finally {
                JdbcUtil.cleanUp(connection, pstmt, rs);
            }
        } 
        // Handle adding a new student request
        else if (request.getRequestURI().endsWith("addstudent")) {
            try {
                student = new StudentDto();
                String sid = request.getParameter("student_id");
                String sname = request.getParameter("student_name");
                String sdept = request.getParameter("student_department");
                String syear = request.getParameter("student_year");
                String scourse = request.getParameter("student_course");
                String sphone = request.getParameter("student_phone");
                String semail = request.getParameter("student_email");
                String spassword = request.getParameter("student_password");

                // Validate that all fields are filled
                if (sid == null || sid.trim().isEmpty() || sname == null || sname.trim().isEmpty() ||
                        sdept == null || sdept.trim().isEmpty() || syear == null || syear.trim().isEmpty() ||
                        scourse == null || scourse.trim().isEmpty() || sphone == null || sphone.trim().isEmpty() ||
                        semail == null || semail.trim().isEmpty() || spassword == null || spassword.trim().isEmpty()) {
                    request.setAttribute("status", "Please fill in all the fields.");
                    request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);
                    return;
                }

                // Set student details
                student.setStudentId(Integer.parseInt(sid));
                student.setStudentName(sname);
                student.setStudentDepartment(sdept);
                student.setStudentYear(Integer.parseInt(syear));
                student.setStudentCourse(scourse);
                student.setStudentPhoneno(sphone);
                student.setStudentEmail(semail);
                student.setStudentPassword(spassword);

                // Call service to add student
                String status = studentService.addStudent(student);
                request.setAttribute("status", status);
                request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("status", "Invalid Student ID or Year format.");
                request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("status", "An error occurred while adding the student.");
                request.getRequestDispatcher("../addstudentstatus.jsp").forward(request, response);
            }
        } 
        // Handle deleting a student request
        else if (request.getRequestURI().endsWith("deletestudent")) {
            try {
                String sidToDelete = request.getParameter("student_id");
                if (sidToDelete == null || sidToDelete.trim().isEmpty()) {
                    request.setAttribute("status", "Please provide Student ID to delete.");
                    request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);
                    return;
                }
                Integer studentIdToDelete = Integer.parseInt(sidToDelete);
                String deleteResult = studentService.deleteStudent(studentIdToDelete); // Get the actual message

                // Forward based on deletion status
                if ("Student deleted successfully.".equals(deleteResult)) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", deleteResult);
                }

                request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("status", "failure");
                request.setAttribute("errorMessage", "Invalid Student ID format.");
                request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("status", "failure");
                request.setAttribute("errorMessage", "An error occurred while deleting the student.");
                request.getRequestDispatcher("../deletestudentstatus.jsp").forward(request, response);
            }
        } 
        // Handle updating a student request
        else if (request.getRequestURI().endsWith("updatestudent")) {
            try {
                student = new StudentDto();
                String sidToUpdate = request.getParameter("student_id");
                String sname = request.getParameter("student_name");
                String sdept = request.getParameter("student_department");
                String syear = request.getParameter("student_year");
                String scourse = request.getParameter("student_course");
                String sphone = request.getParameter("student_phone");
                String semail = request.getParameter("student_email");
                String spassword = request.getParameter("student_password");

                if (sidToUpdate == null || sidToUpdate.trim().isEmpty()) {
                    request.setAttribute("status", "Please provide Student ID to update.");
                    request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);
                    return;
                }

                // Set updated student details
                student.setStudentId(Integer.parseInt(sidToUpdate));
                student.setStudentName(sname);
                student.setStudentDepartment(sdept);
                student.setStudentYear(Integer.parseInt(syear));
                student.setStudentCourse(scourse);
                student.setStudentPhoneno(sphone);
                student.setStudentEmail(semail);
                student.setStudentPassword(spassword);

                // Call service to update student
                String updateResult = studentService.updateStudent(student);

                // Forward based on update result
                if ("Student updated successfully.".equals(updateResult)) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failure");
                    request.setAttribute("errorMessage", updateResult);
                }

                request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("status", "failure");
                request.setAttribute("errorMessage", "Invalid Student ID or Year format.");
                request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("status", "failure");
                request.setAttribute("errorMessage", "An error occurred while updating the student.");
                request.getRequestDispatcher("../updatestudentstatus.jsp").forward(request, response);
            }
        } 
        // Handle searching for a student request
        else if (request.getRequestURI().endsWith("searchstudent")) {
            try {
                String sidToSearch = request.getParameter("student_id");
                if (sidToSearch == null || sidToSearch.trim().isEmpty()) {
                    request.setAttribute("error", "Please provide Student ID to search.");
                    request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
                    return;
                }
                Integer studentIdToSearch = Integer.parseInt(sidToSearch);
                student = studentService.searchStudent(studentIdToSearch);
                request.setAttribute("student", student);
                request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.setAttribute("student", null);
                request.setAttribute("error", "Invalid Student ID format.");
                request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("student", null);
                request.setAttribute("error", "An error occurred while searching for the student.");
                request.getRequestDispatcher("../searchstudentstatus.jsp").forward(request, response);
            }
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        }
    }
}
