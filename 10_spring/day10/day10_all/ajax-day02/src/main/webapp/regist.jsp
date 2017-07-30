<%@ page 
contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<script type="text/javascript"
	src="js/jquery-1.4.3.js">
	</script>
	<script type="text/javascript">
		$(function(){
			$('#username').blur(function(){
				$.ajax({
					"url":"check_uname.do",
					"type":"get",
					"dataType":"text",
					"data":"username=" 
					+ $('#username').val(),
					"success":function(data){
						$('#username_msg').html(data);
					}
				});
			});
		});
	</script>
</head>
<body style="font-size:30px;">
	<form action="regist.do" method="post">
		用户名:<input id="username" 
		name="username"/>
		<span id="username_msg"></span>
		<br/>
		密码:<input type="password" 
		name="pwd"/><br/>
		<input type="submit" value="注册"/>
	</form>
</body>
</html>