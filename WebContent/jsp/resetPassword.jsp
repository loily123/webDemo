<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>重设密码</title>
<script type="text/javascript" src="/webDemo/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#submit").click(
				function() {
					event.returnValue = false;
					if ($("#oldPwd").val() == "" || $("#newPwd").val() == ""
							|| $("confirmPwd").val() == "") {
						alert("请填写完整");
						return;
					}
					if ($("#oldPwd").val() != "${admin.password}") {
						alert("旧密码不正确");
						return;
					}
					if ($("#newPwd").val() == "${admin.password}") {
						alert("新密码不能与旧密码相同");
						return;
					}
					if ($("#newPwd").val() != $("#confirmPwd").val()) {
						alert("两次密码不一致");
						return;
					}
					event.returnValue = true;
					$("form").submit();
				});
	});
</script>
</head>
<body>
	<form action="/webDemo/resetPassword.do" method="post">
		原密码： <input type="text" name="oldPassword" id="oldPwd"></br> 新密码： <input
			type="password" name="newPassword" id="newPwd"></br> 确认密码: <input
			type="password" name="confirmPassword" id="confirmPwd"></br>
		<button id="submit">重置密码</button>
	</form>
</body>
</html>