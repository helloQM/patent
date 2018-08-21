package com.qimou.sb.web.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javassist.expr.NewArray;

@Configuration
public class Converter extends WebMvcConfigurerAdapter implements Filter {

	// 标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
	String NO_LOGIN = "您还未登录";

	// 不需要登录就可以访问的路径(比如:注册登录等)
	static Set<String> includeUrls = new HashSet<String>();//new String[] { "/login", "register" };
	static{
		includeUrls.add("/user/login.html");
		includeUrls.add("/login");
		includeUrls.add("register");
		includeUrls.add("/init");
		includeUrls.add("/yzm1.png");
		includeUrls.add("/uploadfile.html");
		includeUrls.add("/gotoLoginPage");
		includeUrls.add("/doLogin");
	}

	// 自定义消息转化器的第二种方法
	/*
	 * @Override public void
	 * configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	 * StringHttpMessageConverter converter = new
	 * StringHttpMessageConverter(Charset.forName("UTF-8"));
	 * converters.add(converter); }
	 */

	// 自定义SpringMVC的配置:拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				
				// 是否需要过滤
				if(isNeedFilter(request.getRequestURI())){
					if(request.getSession()==null||(request.getSession()!=null&&request.getSession().getAttribute("userID")==null)){
						System.out.println("pre自定义拦截器............session 过期");
						response.sendRedirect(request.getContextPath() + "/gotoLoginPage");
						return false;
					}
					System.out.println("pre自定义拦截器............");
					return true;
				}else{
					return true;
				}
			}

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				System.out.println("post自定义拦截器............");
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
					Exception ex) throws Exception {
				System.out.println("afterCompletion自定义拦截器............");
			}

		};
		registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
	}

	/**
	 * 配置过滤器
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器  init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("过滤器  doFilter");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		System.out.println("filter url:" + uri);

		// 是否需要过滤
		boolean needFilter = isNeedFilter(uri);
		needFilter=false;//先屏蔽过滤器
		if (!needFilter) { // 不需要过滤直接传给下一个过滤器
			filterChain.doFilter(servletRequest, servletResponse);
		} else { // 需要过滤器
			// session中包含user对象,则是登录状态
			if (session != null && session.getAttribute("user") != null) {
				// System.out.println("user:"+session.getAttribute("user"));
				filterChain.doFilter(request, response);
			} else {
				String requestType = request.getHeader("X-Requested-With");
				// 判断是否是ajax请求
				if (requestType != null && "XMLHttpRequest".equals(requestType)) {
					response.getWriter().write(this.NO_LOGIN);
				} else {
					// 重定向到登录页(需要在static文件夹下建立此html文件)
					response.sendRedirect(request.getContextPath() + "/user/login.html");
				}
				return;
			}
		}

	}

	public boolean isNeedFilter(String uri) {
		for (String includeUrl : includeUrls) {
			if (includeUrl.equals(uri)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void destroy() {
		System.out.println("过滤器  destroy");
	}
}
