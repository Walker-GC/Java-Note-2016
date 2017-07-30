<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" >
<title>login</title>
</head>
<body>
	<c:url var="url" value="/user/login.action" />
	<form action="${url}"  method="post">
		<div><label>用户：</label>
		<input type="text" name="user.name" /></div>
		<div><label>密码：</label>
		<input type="password" name="user.pwd" /></div>
		<div><input type="submit" value="登录" /></div>
	</form>
</body>
</html>