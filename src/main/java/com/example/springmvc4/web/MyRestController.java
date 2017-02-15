package com.example.springmvc4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc4.service.DemoService;

@RestController
public class MyRestController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping(value="/testRest",produces="text/plain;charset=utf-8")
	public @ResponseBody String testRest() {
		return demoService.saySomething();
	}
}
