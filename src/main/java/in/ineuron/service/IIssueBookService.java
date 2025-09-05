package in.ineuron.service;

import java.util.List;

import in.ineuron.dto.IssueDto;

public interface IIssueBookService {
    public String issuebook(IssueDto issue);
    public IssueDto returnbook(Integer bookId, Integer studentId); // Corrected method signature
    public String checkdue(Integer studentId);
 // in.ineuron.service.IIssueBookService.java
    List<IssueDto> fetchIssuedBooks(Integer studentId);
    

}
