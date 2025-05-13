package in.ineuron.service;

import in.ineuron.dao.IStudentDao;
import in.ineuron.daofactory.StudentDaoFactory;
import in.ineuron.dto.StudentDto;

public class StudentServiceImpl implements IStudentService {

    private IStudentDao studentDao;

    @Override
    public String addStudent(StudentDto student) {
        studentDao = StudentDaoFactory.getStudentDao();
        return studentDao.addStudent(student);
    }

    @Override
    public StudentDto searchStudent(Integer studentId) {
        studentDao = StudentDaoFactory.getStudentDao();
        return studentDao.searchStudent(studentId);
    }

    @Override
    public String deleteStudent(Integer studentId) {
        studentDao = StudentDaoFactory.getStudentDao();
        return studentDao.deleteStudent(studentId);
    }

    @Override
    public String updateStudent(StudentDto student) {
        studentDao = StudentDaoFactory.getStudentDao();
        return studentDao.updateStudent(student);
    }
}
