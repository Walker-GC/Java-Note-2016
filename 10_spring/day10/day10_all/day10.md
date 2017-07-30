# 1. json (javascript object notation)
## (1)json是什么?    
是一种轻量级的数据交换格式。<br/>
a. 数据交换:<br/>
将数据转换成一种与平台无关的数据格式，发送给接收方来
处理。<br/>
b. 轻量级:<br/>
json相对xml而言，数据量更小，解析速度更快。<br/>
## (2)基本语法
a. 如何表示一个对象? <br/>
	{属性名：属性值，属性名：属性值...} <br/>
注：<br/>
属性名必须使用双引号括起来。<br/>
属性值的类型可以是string,number,true/false,null,
object。<br/>
属性值如果是string,必须使用双引号括起来。<br/>
b. 如何表示由对象组成的数组?<br/>
[{},{},{}...] <br/>

## (3)使用json
a. 如何java对象转换成json字符串。<br/>
使用json官方提供的工具(json-lib)。<br/>
单个对象，使用JSONObject;多个对象组成的数组或者
集合使用JSONArray。<br/>
b. 如何将json字符串转换成javascript对象?
使用prototype提供的工具。<br/>
![json](json.png)

# 2. jQuery对ajax的支持
## $.ajax函数
(1)语法:<br/>
	$.ajax({选项参数名:选项参数值,...}); <br/>
常见的选项参数：<br/>
	url: 请求地址  <br/>
    type: 请求类型 (比如  get/post) <br/>
    data: 请求参数，有两种格式：<br/>
		第一种: 请求字符串形式，比如
			"username=Sally&age=22" <br/>
		第二种：请求对象形式，比如
			{"username":"Sally","age":22} <br/>
	dataType: 服务器端返回的数据类型:<br/>
			json  (json字符串)  <br/>
			text  (文本)  <br/>
			html  (html内容) <br/>
			xml   (xml文档) <br/>
			script (javascript脚本) <br/>
	success: 指定一个函数，用来处理服务器返回的
			数据。<br/>
	error: 指定一个函数，用来处理服务器异常情况。
	async: 同步还是异步(缺省),false表示同步。
		

## load函数



	

			