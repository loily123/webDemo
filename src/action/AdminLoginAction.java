package action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import entity.Admin;
import service.AdminService;
import util.ErrorCode;

public class AdminLoginAction {
	Logger log = Logger.getLogger(AdminLoginAction.class);
	String errorCode;
	private AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String adminQuery(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		log.info(username + password);
		List<Admin> admins = adminService.queryByUserName(username);
		if (admins != null) {
			if (password.equals(admins.get(0).getPassword())) {
				request.getSession().setAttribute("admin", admins.get(0));
				return "success";
			} else {
				errorCode = ErrorCode.PASSWORDERROR;
				request.setAttribute("errorCode", errorCode);
				return "error";
			}
		}
		errorCode = ErrorCode.USERNOTEXIST;
		request.setAttribute("errorCode", errorCode);
		return "error";
	}
}
