package in.ineuron.dao;

import in.ineuron.dto.StudentDto;

public interface IStudentDao {
    String addStudent(StudentDto student);
    StudentDto searchStudent(Integer studentId);
    String updateStudent(StudentDto student);
    String deleteStudent(Integer studentId);
}
