package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.StudentDto;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {

    @Override
    public String addStudent(StudentDto student) {
        String sql = "INSERT INTO studentdb (studentId, studentName, studentDepartment, studentYear, studentCourse, studentPhoneno, studentEmailId, studentPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getStudentName());
            ps.setString(3, student.getStudentDepartment());
            ps.setInt(4, student.getStudentYear());
            ps.setString(5, student.getStudentCourse());
            ps.setString(6, student.getStudentPhoneno());
            ps.setString(7, student.getStudentEmail());
            ps.setString(8, student.getStudentPassword());

            int rows = ps.executeUpdate();
            return rows > 0
                ? "Student added successfully."
                : "Failed to add student. Check ID/email uniqueness.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during insertion.";
        }
    }

    @Override
    public StudentDto searchStudent(Integer studentId) {
        String sql = "SELECT StudentId, StudentName, StudentDepartment, StudentYear, StudentCourse, StudentPhoneno, StudentEmailId FROM studentdb WHERE StudentId=?";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StudentDto s = new StudentDto();
                    s.setStudentId(rs.getInt(1));
                    s.setStudentName(rs.getString(2));
                    s.setStudentDepartment(rs.getString(3));
                    s.setStudentYear(rs.getInt(4));
                    s.setStudentCourse(rs.getString(5));
                    s.setStudentPhoneno(rs.getString(6));
                    s.setStudentEmail(rs.getString(7));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String updateStudent(StudentDto student) {
        String sql = "UPDATE studentdb SET StudentName=?, StudentEmailId=?, StudentYear=?, StudentDepartment=?, StudentCourse=?, StudentPhoneno=?, StudentPassword=? WHERE StudentId=?";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentEmail());
            ps.setInt(3, student.getStudentYear());
            ps.setString(4, student.getStudentDepartment());
            ps.setString(5, student.getStudentCourse());
            ps.setString(6, student.getStudentPhoneno());
            ps.setString(7, student.getStudentPassword());
            ps.setInt(8, student.getStudentId());

            int rows = ps.executeUpdate();
            return rows > 0 ? "Student updated successfully." : "Student not found.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during update.";
        }
    }
    @Override
    public String deleteStudent(Integer studentId) {
        String checkSql =
          "SELECT COUNT(*) FROM issuedb " +
          "WHERE studentId = ? AND returnStatus IS NULL";
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
                return rows > 0
                    ? "Student deleted successfully."
                    : "Student not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during deletion.";
        }
    }

}
