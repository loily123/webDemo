package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.Admin;
import service.AdminService;
import util.ErrorCode;

public class ResetPasswordAction {
	Logger log = Logger.getLogger(ResetPasswordAction.class);
	private AdminService adminService;
	private String errorCode;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String name = ((Admin) request.getSession().getAttribute("admin")).getUserName();
		String password = request.getParameter("newPassword");
		boolean updateResult = adminService.updateAdmin(name, password);
		if (updateResult) {
			request.getSession().invalidate();
			return "success";
		}
		errorCode = ErrorCode.UPDATEFAIL;
		request.setAttribute("errorCode", errorCode);
		return "error";
	}
}
