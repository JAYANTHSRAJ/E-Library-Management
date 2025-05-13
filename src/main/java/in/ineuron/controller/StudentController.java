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
import in.ineuron.util.JdbcUtil;

@WebServlet("/StudentController/*")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request URI: " + request.getRequestURI());
        StudentDto student = null;
        IStudentService studentService = StudentServiceFactory.getStudentService();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        
        if (request.getRequestURI().endsWith("studentlogin")) {
            try {
                connection = JdbcUtil.getJdbcConnection();
                String sqlSelectQuery = "SELECT studentId, studentEmailId, studentPassword FROM studentdb WHERE studentId=?";
                pstmt = connection.prepareStatement(sqlSelectQuery);
                String studentIdParam = request.getParameter("student_id");
                
                if (studentIdParam != null && !studentIdParam.trim().isEmpty()) {
                    pstmt.setInt(1, Integer.parseInt(studentIdParam));
                } else {
                    request.setAttribute("error", "Please enter Student ID.");
                    request.getRequestDispatcher("../studentVerificationFail.jsp").forward(request, response);
                    return;
                }

                rs = pstmt.executeQuery();
                RequestDispatcher rd = null;

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
                        rd = request.getRequestDispatcher("../studentVerificationFail.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    // Student ID not found in the database
                    System.out.println("Invalid Student ID: " + studentIdParam);
                    request.setAttribute("error", "Invalid Student ID.");
                    rd = request.getRequestDispatcher("../studentVerificationFail.jsp");
                    rd.forward(request, response);
                }
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
        }
    }
}
