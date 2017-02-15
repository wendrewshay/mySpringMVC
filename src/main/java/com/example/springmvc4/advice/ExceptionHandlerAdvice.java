package com.example.springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //声明一个控制器建言
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value=Exception.class) //全局异常处理
	public ModelAndView exception(Exception exception, WebRequest webRequest) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
	
	@ModelAttribute //将键值对添加到全局，所有注解@RequestMapping的方法都可以获得此键值对
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息");
	}
	
	@InitBinder //注解定制WebDataBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}
}
