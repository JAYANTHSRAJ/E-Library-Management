package in.ineuron.service;

import in.ineuron.dto.StudentDto;

public interface IStudentService {
    String addStudent(StudentDto student);
    StudentDto searchStudent(Integer studentId);
    String deleteStudent(Integer studentId);
    String updateStudent(StudentDto student);

    // ✅ New method for student request
    String saveStudentRequest(StudentDto student);
    StudentDto getStudentByEmail(String email) throws Exception;
    String getStudentEmailById(int studentId);

    String changePassword(Integer studentId, String oldPassword, String newPassword) throws Exception;

    String getStudentEmailById(Integer studentId);
}
