package in.ineuron.daofactory;

import in.ineuron.dao.AdminDaoImpl;
import in.ineuron.dao.IAdminDao;

public class AdminDaoFactory {
	private AdminDaoFactory() {}
	private static IAdminDao admindao = null;
	public static IAdminDao getadmindao() {
		if(admindao == null) {
			admindao = new AdminDaoImpl();
		}
		return admindao;
	}
	
}
