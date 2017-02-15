package com.example.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.springmvc4.MyMvcConfig;
import com.example.springmvc4.service.DemoService;

/**
 * 测试用例
 * 1.@WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebAppliationContext。
 *   它的属性指定的是Web资源的位置，默认为src/main/webapp，本例修改为src/main/resources。
 * @author xiawq
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")//1
public class TestControllerIntegrationTests {

	private MockMvc mockMvc; //2 模拟MVC对象
	
	@Autowired
	private DemoService demoService;//3
	
	@Autowired
	WebApplicationContext wac;//4
	
	@Autowired
	MockHttpSession session;//5
	
	@Autowired
	MockHttpServletRequest request;//6
	
	@Before //7 测试开始前进行的初始化工作
	public void setup() {
		this.mockMvc = 
				MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testMormalController() throws Exception {
		mockMvc.perform(get("/normal")) //8 模拟向/normal进行get请求
				.andExpect(status().isOk()) //9 预期控制返回状态为200
				.andExpect(view().name("page")) //10 预期view的名称为page
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp")) //11 预期页面转向的真正路径
				.andExpect(model().attribute("msg", demoService.saySomething())); //12 预期model里的值
		
	}
	
	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/testRest")) //13 模拟向/testRest进行get请求
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")) //14 预期返回值的媒体类型
				.andExpect(content().string(demoService.saySomething())); //15 //预期返回值的内容
		
	}
}
