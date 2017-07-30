<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 引入Struts标签 -->
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Test</h1>
	<!--利用ONGL表达方式获取数据  -->
	<s:property value="message"/>
	<s:property value="#session.loginName"/><br />
	<s:property value="[1].message"/><br />
	<s:property value="[0].message"/><br />
	<!--利用EL表达式-->
	${message}<br />
	${loginName}<br />
	<!--debug标签可以输出-->
	<s:debug></s:debug>
</body>
</html>