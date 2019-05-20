<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error page</title>
</head>
<script type="text/javascript" src="/webDemo/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var errorCode = "${errorCode}";
		var msg = "";
		switch (errorCode) {
		case "001":
			msg = "密码错误";
			alert(errorCode + "\n" + msg);
			location.href = "/webDemo/jsp/adminLogin.jsp";
			break;
		case "002":
			msg = "用户不存在";
			alert(errorCode + "\n" + msg);
			location.href = "/webDemo/jsp/adminLogin.jsp";
			break;
		case "003":
			msg="重置密码失败";
			alert(errorCode + "\n" + msg);
			location.href = "/webDemo/jsp/resetPassword.jsp";
			break;
		default:
			msg = "未知错误";
			break;
		}
		

	})
</script>
<body>

</body>
</html>