<%@ page 
 contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
	<style>
		#d1{
			width:480px;
			height:350px;
			margin-left:400px;
			margin-top:80px;
			background-color:black;
		}
		#d2{
			color:yellow;
			background-color:red;
			height:32px;
		}
		table{
			color:white;
			font-style:italic;
			font-size:24px;
		}
	</style>
	<script type="text/javascript"
	src="js/jquery-1.4.3.js">
	</script>
	<script type="text/javascript">
		$(function(){
			//页面加载完成
			setInterval(quoto,5000);
		});
		function quoto(){
			$.ajax({
				"url":"quoto.do",
				"type":"post",
				"dataType":"json",
				"success":function(data){
					//当服务器处理正常:
					/*
						如果服务器返回的是json
						字符串，$.ajax函数会
						自动将这个json字符串
						转换成javascript对象。
					*/
					$('#tb1').empty();
					for(i = 0; i < data.length; i ++){
						var s = data[i];
						$('#tb1').append(
							'<tr><td>' + s.code 
							+ '</td><td>' + s.name 
							+ '</td><td>' + s.price
							+ '</td></tr>');
					}
				},
				"error":function(){
					//当服务器处理发生异常:
					alert("出错");
				}
			});
		}
	</script>
</head>
<body style="font-size:30px;">
	<div id="d1">
		<div id="d2">股票实时行情</div>
		<div id="d3">
			<table width="100%">
				<thead>
					<tr>
						<td>代码</td>
						<td>名称</td>
						<td>价格</td>
					</tr>
				</thead>
				<tbody id="tb1">
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>