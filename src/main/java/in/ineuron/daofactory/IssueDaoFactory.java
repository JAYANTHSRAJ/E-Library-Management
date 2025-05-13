package in.ineuron.daofactory;

import in.ineuron.dao.IIssueBookDao;
import in.ineuron.dao.IssueBookDaoImpl;

public class IssueDaoFactory {
	private IssueDaoFactory() {}
	private static IIssueBookDao issuedao = null;
	public static IIssueBookDao getissuebookdao() {
		if(issuedao == null) {
			issuedao = new IssueBookDaoImpl();
		}
		return issuedao;
	}
}
