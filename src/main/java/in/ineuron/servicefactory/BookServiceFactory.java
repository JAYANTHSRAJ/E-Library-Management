package in.ineuron.servicefactory;

import in.ineuron.service.*;

public class BookServiceFactory {
	private BookServiceFactory() {}
	private static IBookService bookservice = null;
	public static IBookService getbookservice() {
		if(bookservice == null) {
			bookservice = new BookServiceImpl();
		}
		return bookservice;
	}
}