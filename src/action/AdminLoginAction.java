package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.AdminService;

public class AdminLoginAction {
	Logger log = Logger.getLogger(AdminLoginAction.class);
	private AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String adminQuery(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info(username + password);
		adminService.queryByUserName(username, password);
		return "success";
	}
}
