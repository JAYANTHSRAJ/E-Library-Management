package in.ineuron.daofactory;

import in.ineuron.dao.*;

public class BookDaoFactory {
	private BookDaoFactory() {}
	private static IBookDao bookdao=null;
	public static IBookDao getbookdao() {
		if(bookdao == null) {
			bookdao = new BookDaoImpl();
		}
		return bookdao;
	}
}