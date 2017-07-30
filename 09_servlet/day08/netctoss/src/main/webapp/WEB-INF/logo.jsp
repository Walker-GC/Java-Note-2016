<%@page pageEncoding="utf-8"%>
<!-- 
	EL默认从4个隐含对象中取值：
	page、request、session、application。
	不包括cookie，并且cookie不是隐含对象。
	若使用EL从cookie中取值，语法如下：
		cookie.参数名.value
	其中单词cookie和value固定，参数名有变化。
-->
<img src="images/logo.png" alt="logo" class="left"/>
<span>欢迎您，${cookie.adminCode.value }</span>
<a href="#">[退出]</a>