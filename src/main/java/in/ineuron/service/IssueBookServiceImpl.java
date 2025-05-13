package in.ineuron.service;



import java.util.List;          
  

import in.ineuron.dao.IIssueBookDao;
import in.ineuron.daofactory.IssueDaoFactory;
import in.ineuron.dto.IssueDto;

public class IssueBookServiceImpl implements IIssueBookService {

    private IIssueBookDao issuedao;

    @Override
    public String issuebook(IssueDto issue) {
        issuedao = IssueDaoFactory.getissuebookdao();
        return issuedao.issuebook(issue);
    }

    @Override
    public IssueDto returnbook(Integer bookId, Integer studentId) {
        issuedao = IssueDaoFactory.getissuebookdao();
        return issuedao.returnbook(bookId, studentId); // Corrected to match interface and DAO
    }

    @Override
    public String checkdue(Integer studentId) {
        issuedao = IssueDaoFactory.getissuebookdao();
        return issuedao.checkDue(studentId);
    }
    @Override
    public List<IssueDto> fetchIssuedBooks(Integer studentId) {
        IIssueBookDao dao = IssueDaoFactory.getissuebookdao();
        return dao.fetchIssuedBooks(studentId);
    }
}
