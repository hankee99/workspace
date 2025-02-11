package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.iei.util.AdminInterceptor;
import kr.co.iei.util.LoginInterceptor;

//스프링부트 설정파일임을 나타내는 어노테이션
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Value(value="${file.root}")
	private String root;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/**")
			.addResourceLocations("classpath:/templates/","classpath:/static/");
		
		registry
			.addResourceHandler("/photo/**")
			.addResourceLocations("file:///"+root+"/photo/");
		registry
			.addResourceHandler("/notice/editor/**")
			.addResourceLocations("file:///"+root+"/notice/editor/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/member/logout",
								"/member/mypage",
								"/member/update",
								"/member/mypage2",
								"/member/delete",
								"/notice/**",
								"/admin/**")
				.excludePathPatterns("/notice/list",
									"/notice/view",
									"/notice/filedown",
									"/notice/editor/**");
		
		registry.addInterceptor(new AdminInterceptor())
				.addPathPatterns("/admin/**");
	}
	
}
