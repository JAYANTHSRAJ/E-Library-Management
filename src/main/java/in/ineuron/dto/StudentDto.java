package in.ineuron.dto;

public class StudentDto {
<<<<<<< HEAD
    private String  studentId;
    private String studentName;
    private String studentEmail;
=======
    private Integer studentId;
    private String studentName;
    private String studentEmail;
   
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    private Integer studentYear;
    private String studentDepartment;
    private String studentCourse;
    private String studentPhoneno;
    private String studentPassword;
<<<<<<< HEAD
    private String status; // <-- NEW FIELD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8

    public StudentDto() {
    }

<<<<<<< HEAD
    public StudentDto(String  studentId, String studentName, String studentEmail,
                      Integer studentYear, String studentDepartment, String studentCourse,
                      String studentPhoneno, String studentPassword, String status) {
=======
    public StudentDto(Integer studentId, String studentName, String studentEmail,
                      Integer studentYear, String studentDepartment, String studentCourse,
                      String studentPhoneno, String studentPassword) {
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentYear = studentYear;
        this.studentDepartment = studentDepartment;
        this.studentCourse = studentCourse;
        this.studentPhoneno = studentPhoneno;
        this.studentPassword = studentPassword;
<<<<<<< HEAD
        this.status = status;
    }

    // --- getters & setters ---
    public String  getStudentId() {
        return studentId;
    }
    public void setStudentId(String  i) {
        this.studentId = i;
=======
    }


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    }

    public String getStudentName() {
        return studentName;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

<<<<<<< HEAD
    public Integer getStudentYear() {
        return studentYear;
    }
=======
  

    public Integer getStudentYear() {
        return studentYear;
    }

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getStudentCourse() {
        return studentCourse;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentPhoneno() {
        return studentPhoneno;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentPhoneno(String studentPhoneno) {
        this.studentPhoneno = studentPhoneno;
    }

    public String getStudentPassword() {
        return studentPassword;
    }
<<<<<<< HEAD
=======

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

<<<<<<< HEAD
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentDto [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
                + ", studentYear=" + studentYear + ", studentDepartment=" + studentDepartment
                + ", studentCourse=" + studentCourse + ", studentPhoneno=" + studentPhoneno
                + ", studentPassword=" + studentPassword + ", status=" + status + "]";
    }
}
=======
    @Override
    public String toString() {
        return "StudentDto [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
                + ", studentYear=" + studentYear + ", studentDepartment="
                + studentDepartment + ", studentCourse=" + studentCourse + ", studentPhoneno=" + studentPhoneno
                + ", studentPassword=" + studentPassword + "]";
    }
}
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
