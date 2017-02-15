package com.example.springmvc4;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.springmvc4.interceptor.DemoInterceptor;
import com.example.springmvc4.messageconverter.MyMessageConverter;

/**
 * MVC统一配置
 * @author qxb-810
 *
 */

@Configuration
@EnableWebMvc
@EnableScheduling // 开启计划任务支持
@ComponentScan("com.example.springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = 
				new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/classes/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000);
		return multipartResolver;
	}
	
	@Bean
	public MyMessageConverter converter() {
		return new MyMessageConverter();
	}
	
	/**
	 * 注册静态资源处理器
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}

	/**
	 * 集中管理页面转向，简化配置
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/converter").setViewName("/converter");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}

	/**
	 * 可不忽略后缀，如：http://localhost:8080/mvc/index.do
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(true);
	}

	/**
	 * 仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}
	
}
