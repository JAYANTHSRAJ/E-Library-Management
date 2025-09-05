package in.ineuron.dao;

import in.ineuron.dto.StudentDto;

public interface IStudentDao {
    String addStudent(StudentDto student);
    StudentDto searchStudent(Integer studentId);
    String updateStudent(StudentDto student);
    String deleteStudent(Integer studentId);
    // ✅ New method
    String saveStudentRequest(StudentDto student);
  
    
 // Change password
    String changePassword(int studentId, String oldPassword, String newPassword);

    // Get student email by ID
    String getStudentEmailById(int studentId);
}
