package in.ineuron.dto;

import java.io.Serializable;

public class IssueDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer bookId;
    private String bookName;           
    private String bookIssueDate;
    private String bookIssueDueDate;
    private Integer studentId;
    private String returnDate;


    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getBookIssueDate() { return bookIssueDate; }
    public void setBookIssueDate(String d) { this.bookIssueDate = d; }

    public String getBookIssueDueDate() { return bookIssueDueDate; }
    public void setBookIssueDueDate(String d) { this.bookIssueDueDate = d; }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer s) { this.studentId = s; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String d) { this.returnDate = d; }
}
