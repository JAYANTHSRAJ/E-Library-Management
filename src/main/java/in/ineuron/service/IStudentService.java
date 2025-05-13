package in.ineuron.service;

import in.ineuron.dto.StudentDto;

public interface IStudentService {
    String addStudent(StudentDto student);
    StudentDto searchStudent(Integer studentId);
    String deleteStudent(Integer studentId);
    String updateStudent(StudentDto student);
}
