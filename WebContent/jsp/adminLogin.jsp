<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin登录页面</title>
<script type="text/javascript" src="/webDemo/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			var username = $("#username").val();
			var password = $("#password").val();
			if (username == "" || password == "") {
				alert("请将信息填写完整");
				return;
			}
		});
	});
</script>
</head>
<body bgcolor="pink">
	<form action="" method="post">
		<table style="height: 100%; width: 100%;">
			<tr align="center">
				<td colspan="3" bgcolor="gray"><font size="20" color="white">欢迎进入网页Demo系统</font></td>
			</tr>
			<tr style="height: 100px;"></tr>
			<tr>
				<td align="right" width="45%"><font size="10">用户名</font></td>
				<td><input type="text" name="username"
					style="height: 50px; margin-left: 50px; width: 240px;"
					id="username"></td>
			</tr>
			<tr style="height: 80px;"></tr>
			<tr>
				<td align="right" width="45%"><font size="10">密&nbsp;&nbsp;&nbsp;&nbsp;码</font></td>
				<td><input type="text" name="password"
					style="height: 50px; margin-left: 50px; width: 240px;"
					id="password"></td>
			</tr>
			<tr style="height: 50px;"></tr>
			<tr align="center">
				<td colspan="3">
					<button style="background: #99FFFF">
						<font size="10px" id="login">登录</font>
					</button>
				</td>
			</tr>
			<tr style="height: 40px;"></tr>
			<tr align="center">
				<td colspan="3"><font size="6" color="#0066FF">Demo系统为您服务
						2810637851@qq.com</font></td>
			</tr>
		</table>
	</form>
</body>
</html>