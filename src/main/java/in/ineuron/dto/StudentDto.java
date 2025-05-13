package in.ineuron.dto;

public class StudentDto {
    private Integer studentId;
    private String studentName;
    private String studentEmail;
   
    private Integer studentYear;
    private String studentDepartment;
    private String studentCourse;
    private String studentPhoneno;
    private String studentPassword;


    public StudentDto() {
    }

    public StudentDto(Integer studentId, String studentName, String studentEmail,
                      Integer studentYear, String studentDepartment, String studentCourse,
                      String studentPhoneno, String studentPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentYear = studentYear;
        this.studentDepartment = studentDepartment;
        this.studentCourse = studentCourse;
        this.studentPhoneno = studentPhoneno;
        this.studentPassword = studentPassword;
    }


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

  

    public Integer getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentPhoneno() {
        return studentPhoneno;
    }

    public void setStudentPhoneno(String studentPhoneno) {
        this.studentPhoneno = studentPhoneno;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    @Override
    public String toString() {
        return "StudentDto [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
                + ", studentYear=" + studentYear + ", studentDepartment="
                + studentDepartment + ", studentCourse=" + studentCourse + ", studentPhoneno=" + studentPhoneno
                + ", studentPassword=" + studentPassword + "]";
    }
}