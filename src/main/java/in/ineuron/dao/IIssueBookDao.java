package in.ineuron.dao;
import java.util.List;

import in.ineuron.dto.IssueDto;

public interface IIssueBookDao {
    public String issuebook(IssueDto dto);
   public IssueDto returnbook(Integer bookId, Integer studentId);
    public String checkDue(Integer studentId);


List<IssueDto> fetchIssuedBooks(Integer studentId);

	
}
