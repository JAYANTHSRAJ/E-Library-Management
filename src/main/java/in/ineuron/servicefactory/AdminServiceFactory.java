package in.ineuron.servicefactory;

import in.ineuron.service.IAdminService;
import in.ineuron.service.AdminServiceImpl;

public class AdminServiceFactory {
    private static IAdminService adminService;

    public static IAdminService getadminservice() {
        if (adminService == null) {
            adminService = new AdminServiceImpl();
        }
        return adminService;
    }
}
