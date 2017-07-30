# 1. 基于注解的spring mvc应用
## (1)编程步骤
step1. 导包。<br/>
spring-webmvc <br/>
step2. 添加配置文件。<br/>
step3. 配置DispatcherServlet。<br/>
step4. 写Controller。<br/>
	1.不用实现Controller接口。<br/>
	2.可以添加多个方法。<br/>
	3.方法名不做要求，返回值类型可以是
	ModelAndView,也可以是String。<br/>
	4.在类名前添加@Controller。<br/>
	5.使用@RequestMapping来告诉
	DispatcherServlet,请求路径与处理器的对
	应关系。<br/>
step5. 写jsp。<br/>
step6. spring配置文件中，需要配置：<br/>
组件扫描,mvc注解扫描，视图解析器。<br/>

## (2) 如何读取请求参数值
方式一: 通过request对象。<br/>
方式二：通过@RequestParam注解。<br/>
![param](param2.png)
<br/>
方式三: 封装成一个javabean。<br/>
step1.写一个java类，要求属性名与实际请求参数名一致，
并提供相应的get/set方法。<br/>
step2.在方法中添加该java类型的参数。<br/>

## (3) 向页面传值
方式一：通过request对象。<br/>
将数据绑订到request对象，然后转发给jsp来展现。<br/>
注：springmvc默认使用转发机制。<br/>
方式二：通过session对象。<br/>
将数据绑订到session对象。<br/>
方式三: 通过ModelMap对象。<br/>
在方法当中，添加ModelMap对象作为参数，然后调用该
对象的addAttribute方法。<br/>
方式四：通过ModelAndView对象。<br/>
将处理结果添加到ModelAndView对象里面。

## (4) 如何重定向
情况1：如果方法的返回值是String，在
重定向地址前，添加"redirect:",比如
"redirect:toIndex.do"。<br/>
情况2：如果方法的返回值是ModelAndView:<br/>
RediectView rv = new RedirectView("toIndex.do");<br/>
ModelAndView mav = new ModelAndView(rv);







