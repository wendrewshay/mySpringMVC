package com.example.springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitiallizer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		context.register(MyMvcConfig.class);
		context.setServletContext(servletContext);
		
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);//开启异步方法支持
	}

}
