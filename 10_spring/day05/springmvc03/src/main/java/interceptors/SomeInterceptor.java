package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SomeInterceptor implements 
HandlerInterceptor{

	/**
	 * ex: 处理器方法所抛出的异常。
	 */
	public void afterCompletion(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object arg2, Exception ex)
			throws Exception {
		System.out.println(
				"afterCompletion()");
	}
	

	public void postHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object arg2, ModelAndView mav)
			throws Exception {
		System.out.println("postHandle()");
	}

	/**
	 * arg2: 用来获得处理器方法描述的对象。
	 */
	public boolean preHandle(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Object arg2) throws Exception {
		System.out.println("preHandle()");
		return true;
	}

}

