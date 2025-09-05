package in.ineuron.servicefactory;

import in.ineuron.service.IIssueBookService;
import in.ineuron.service.IssueBookServiceImpl;

public class IssueServiceFactory {
	private IssueServiceFactory() {}
	private static IIssueBookService issueservice=null;
	public static IIssueBookService getissuebookservice() {
		if(issueservice == null) {
			issueservice = new IssueBookServiceImpl();
		}
		return issueservice;
	}
}
