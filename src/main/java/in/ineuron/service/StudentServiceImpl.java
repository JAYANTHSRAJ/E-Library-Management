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
<<<<<<< HEAD

    @Override
    public String saveStudentRequest(StudentDto student) {
        studentDao = StudentDaoFactory.getStudentDao();
        return studentDao.saveStudentRequest(student);
    }

	@Override
	public StudentDto getStudentByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String changePassword(Integer studentId, String oldPassword, String newPassword) throws Exception {
	    studentDao = StudentDaoFactory.getStudentDao();
	    return studentDao.changePassword(studentId, oldPassword, newPassword);
	}

	@Override
	public String getStudentEmailById(Integer studentId) {
	    studentDao = StudentDaoFactory.getStudentDao();
	    return studentDao.getStudentEmailById(studentId);
	}

    @Override
    public String getStudentEmailById(int studentId) {
        return studentDao.getStudentEmailById(studentId);
    }

=======
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
}
