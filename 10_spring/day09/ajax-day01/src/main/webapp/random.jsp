<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<html>
	<head>
		<script type="text/javascript"
		src="js/ajax.js">
		</script>
		<script type="text/javascript">
			function showNumber(){
				var xhr = getXhr();
				xhr.open('get','getNumber.do?' + Math.random(),true);
				xhr.onreadystatechange=function(){
					if(xhr.readyState == 4 && 
							xhr.status == 200){
						var txt = xhr.responseText;
						$('d1').innerHTML = txt;
					}
				};
				xhr.send(null);
				
			}
		</script>
	</head>
	<body style="font-size:30px;">
		<a href="javascript:showNumber();">点这儿，显示一个随机数</a><br/>
		<div id="d1"></div>
	</body>
</html>
