package in.ineuron.daofactory;

import in.ineuron.dao.*;
<<<<<<< HEAD
import in.ineuron.dto.IBookDao;
=======
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8

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