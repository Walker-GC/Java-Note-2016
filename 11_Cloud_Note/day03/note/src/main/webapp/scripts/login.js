/* 登录界面JS脚本程序 */

$(function(){
	//为登录按钮绑定单击事件
	$('#login').click(loginAction);
	$('#count').focus().blur(checkName);
	$('#password').blur(checkPassword);
});
//登录按钮的动作
function loginAction(){
	console.log('login click!');
	//收集用户名和密码数据
	var name = $('#count').val();
	var password = $('#password').val();
	//验证用户名和密码
	var pass = checkName()+checkPassword();  
	if(pass!=2){
		return;
	}
	var paramter={'name':name, 
			'password':password};
	//发送Ajax请求
	$.ajax({
		url:'user/login.do',
		data:paramter,
		dataType:'json',
		type:'POST',
		success:function(result){
			//{state:0,data:, message}
			if(result.state==0){//SUCCESS
				console.log("SUCCESS");
				console.log(result.data);
				location.href='edit.html';
				return;
			}else if(result.state==2){
				//用户名错误
				$('#count-msg').html(result.message);
				return;
			}else if(result.state==3){
				//密码错误
				$('#password-msg').html(result.message);
				return;
			}
			alert(result.message);
		},
		error:function(){
			alert('Ajax请求失败');
		}
	});
	
}

function checkName(){
	var name = $('#count').val();
	if(name==null || name==""){
		//提示错误
		$('#count-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(name)){
		$('#count-msg').html('长度不对');
		return false;
	}
	$('#count-msg').empty();
	return true;
} 





function checkPassword(){
	var password = $('#password').val();
	if(password==null || password==""){
		//提示错误
		$('#password-msg').html('不能空');
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(! reg.test(password)){
		$('#password-msg').html('长度不对');
		return false;
	}
	$('#password-msg').empty();
	return true;
}






