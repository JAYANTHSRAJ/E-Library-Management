package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.StudentDto;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

<<<<<<< HEAD
    // Insert student into student_request table with default status = 'PENDING'
    @Override
    public String addStudent(StudentDto student) {
        String sql = "INSERT INTO student_request " +
                     "(studentId, studentName, studentDepartment, studentYear, " +
                     "studentCourse, studentPhoneno, studentEmailId, studentPassword, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'PENDING')";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	  ps.setString(1, student.getStudentId());
=======
    @Override
    public String addStudent(StudentDto student) {
        String sql = "INSERT INTO studentdb (studentId, studentName, studentDepartment, studentYear, studentCourse, studentPhoneno, studentEmailId, studentPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
            ps.setString(2, student.getStudentName());
            ps.setString(3, student.getStudentDepartment());
            ps.setInt(4, student.getStudentYear());
            ps.setString(5, student.getStudentCourse());
            ps.setString(6, student.getStudentPhoneno());
            ps.setString(7, student.getStudentEmail());
            ps.setString(8, student.getStudentPassword());

            int rows = ps.executeUpdate();
            return rows > 0
<<<<<<< HEAD
                ? "Student request submitted successfully. Awaiting admin approval."
                : "Failed to submit student request.";
=======
                ? "Student added successfully."
                : "Failed to add student. Check ID/email uniqueness.";
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during insertion.";
        }
    }

<<<<<<< HEAD
    // Search student from studentdb (only approved students)
    @Override
    public StudentDto searchStudent(Integer studentId) {
        String sql = "SELECT studentId, studentName, studentDepartment, studentYear, " +
                     "studentCourse, studentPhoneno, studentEmailId, studentPassword " +
                     "FROM studentdb WHERE studentId=?";
=======
    @Override
    public StudentDto searchStudent(Integer studentId) {
        String sql = "SELECT StudentId, StudentName, StudentDepartment, StudentYear, StudentCourse, StudentPhoneno, StudentEmailId FROM studentdb WHERE StudentId=?";
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StudentDto s = new StudentDto();
<<<<<<< HEAD
                    s.setStudentId(rs.getString(1));
=======
                    s.setStudentId(rs.getInt(1));
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                    s.setStudentName(rs.getString(2));
                    s.setStudentDepartment(rs.getString(3));
                    s.setStudentYear(rs.getInt(4));
                    s.setStudentCourse(rs.getString(5));
                    s.setStudentPhoneno(rs.getString(6));
                    s.setStudentEmail(rs.getString(7));
<<<<<<< HEAD
                    s.setStudentPassword(rs.getString(8));
=======
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

<<<<<<< HEAD
    // Update student info in studentdb (not in requests)
    @Override
    public String updateStudent(StudentDto student) {
        String sql = "UPDATE studentdb SET studentName=?, studentEmailId=?, studentYear=?, " +
                     "studentDepartment=?, studentCourse=?, studentPhoneno=?, studentPassword=? " +
                     "WHERE studentId=?";
=======
    @Override
    public String updateStudent(StudentDto student) {
        String sql = "UPDATE studentdb SET StudentName=?, StudentEmailId=?, StudentYear=?, StudentDepartment=?, StudentCourse=?, StudentPhoneno=?, StudentPassword=? WHERE StudentId=?";
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentEmail());
            ps.setInt(3, student.getStudentYear());
            ps.setString(4, student.getStudentDepartment());
            ps.setString(5, student.getStudentCourse());
            ps.setString(6, student.getStudentPhoneno());
            ps.setString(7, student.getStudentPassword());
<<<<<<< HEAD
            ps.setString(8, student.getStudentId());
            
=======
            ps.setInt(8, student.getStudentId());

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
            int rows = ps.executeUpdate();
            return rows > 0 ? "Student updated successfully." : "Student not found.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during update.";
        }
    }
<<<<<<< HEAD

    // Delete student only if no unreturned books
    @Override
    public String deleteStudent(Integer studentId) {
        String checkSql =
          "SELECT COUNT(*) FROM issuedb WHERE studentId = ? AND returnStatus IS NULL";
=======
    @Override
    public String deleteStudent(Integer studentId) {
        String checkSql =
          "SELECT COUNT(*) FROM issuedb " +
          "WHERE studentId = ? AND returnStatus IS NULL";
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        String deleteIssuedSql =
          "DELETE FROM issuedb WHERE studentId = ?";
        String deleteStudentSql =
          "DELETE FROM studentdb WHERE studentId = ?";

        try (Connection conn = JdbcUtil.getJdbcConnection()) {
            // 1) Ensure no unreturned books
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setInt(1, studentId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return "Student has unreturned books. Cannot delete.";
                    }
                }
            }

            // 2) Delete their issuedb history
            try (PreparedStatement ps = conn.prepareStatement(deleteIssuedSql)) {
                ps.setInt(1, studentId);
                ps.executeUpdate();
            }

            // 3) Now delete the student
            try (PreparedStatement ps = conn.prepareStatement(deleteStudentSql)) {
                ps.setInt(1, studentId);
                int rows = ps.executeUpdate();
<<<<<<< HEAD
                return rows > 0 ? "Student deleted successfully." : "Student not found.";
            }

=======
                return rows > 0
                    ? "Student deleted successfully."
                    : "Student not found.";
            }
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during deletion.";
        }
    }
<<<<<<< HEAD
   
    @Override
    public String saveStudentRequest(StudentDto student) {
        String status = "FAILURE";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = JdbcUtil.getJdbcConnection();

            // Correct column names as per your table
            String sql = "INSERT INTO student_requests " +
                         "(student_id, student_name, student_department, student_year, " +
                         "student_course, student_phone, student_email, student_password, status) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, String.valueOf(student.getStudentId())); // since column is varchar
            pstmt.setString(2, student.getStudentName());
            pstmt.setString(3, student.getStudentDepartment());
            pstmt.setString(4, String.valueOf(student.getStudentYear())); // also varchar
            pstmt.setString(5, student.getStudentCourse());
            pstmt.setString(6, student.getStudentPhoneno());
            pstmt.setString(7, student.getStudentEmail());
            pstmt.setString(8, student.getStudentPassword());
            pstmt.setString(9, student.getStatus());

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                status = "SUCCESS";
            }

        } catch (SQLException se) {
            se.printStackTrace();
            status = "SQL ERROR: " + se.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            status = "ERROR: " + e.getMessage();
        } finally {
            JdbcUtil.cleanUp(connection, pstmt, null);
        }
        return status;
    }
    
    
    @Override
    public String changePassword(int studentId, String oldPassword, String newPassword) {
        String result = "failure";
        String sqlCheck = "SELECT studentPassword FROM studentdb WHERE studentId = ?";
        String sqlUpdate = "UPDATE studentdb SET studentPassword = ? WHERE studentId = ?";

        try (Connection con = JdbcUtil.getJdbcConnection();
             PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {

            psCheck.setInt(1, studentId);
            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    String currentPassword = rs.getString("studentPassword");

                    if (currentPassword.equals(oldPassword)) {
                        try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                            psUpdate.setString(1, newPassword);
                            psUpdate.setInt(2, studentId);
                            int rows = psUpdate.executeUpdate();

                            if (rows > 0) {
                                result = "success";
                            }
                        }
                    } else {
                        result = "Old password does not match.";
                    }
                } else {
                    result = "Student not found.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    @Override
    public String getStudentEmailById(int studentId) {
        String email = null;
        String sql = "SELECT studentEmailId FROM studentdb WHERE studentId = ?";

        try (Connection con = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("studentEmailId");
                }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

	
}
=======

}
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
