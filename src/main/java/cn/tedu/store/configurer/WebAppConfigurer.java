package cn.tedu.store.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.tedu.store.interceptor.LoginInterceptor;

/**
 * configure interceptor
 * @author zxh
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> includePath=new ArrayList<>();
		List<String> excludePath=new ArrayList<>();
		// add to include/exclude list
		includePath.add("/user/**");
		excludePath.add("/user/reg.do");
		excludePath.add("/user/login.do");
		
		// register interceptor
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns(includePath)
			.excludePathPatterns(excludePath);
	}
}
