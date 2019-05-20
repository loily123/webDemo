package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ResetPasswordAction {
	Logger log=Logger.getLogger(ResetPasswordAction.class);
	public String resetPwd(HttpServletRequest request,HttpServletResponse response) {
		
		return "success";
	}
}
