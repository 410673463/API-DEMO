package com.ws.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ws.init.InitRedis;



@Component
public class RequestAccessLimit implements HandlerInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(InitRedis.class);
	@Autowired
	private RedisTemplate<String,Object> redistemplate;
	/**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
    	String ip = getIpAddr(httpServletRequest);
        String url = httpServletRequest.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);
        boolean flag = true;
        if(redistemplate.hasKey(key)){//访问
			int count = (int) redistemplate.opsForValue().get(key);
			redistemplate.delete(key);
			count++;
			redistemplate.opsForValue().set(key,count,60,TimeUnit.SECONDS);
			if(count > 10){
				flag = false;
				throw new Exception("访问受限");
			}
		}else{
			redistemplate.opsForValue().set(key,1,60,TimeUnit.SECONDS);
		}		
        return flag;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    public static final String getIpAddr(final HttpServletRequest request)  
	        throws Exception { 
	    if (request == null) {  
	        throw (new Exception("getIpAddr method HttpServletRequest Object is null"));  
	    }  
	    String ipString = request.getHeader("x-forwarded-for");  
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getRemoteAddr();  
	    }  
	  
	    // 多个路由时，取第一个非unknown的ip  
	    final String[] arr = ipString.split(",");  
	    for (final String str : arr) {  
	        if (!"unknown".equalsIgnoreCase(str)) {  
	            ipString = str;  
	            break;  
	        }  
	    }  
	    return ipString;  
	}
}
