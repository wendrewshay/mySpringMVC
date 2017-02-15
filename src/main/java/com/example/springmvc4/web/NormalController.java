package com.example.springmvc4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springmvc4.service.DemoService;

@Controller
public class NormalController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("/normal")
	public String testPage(Model model) {
		model.addAttribute("msg", demoService.saySomething());
		return "page";
	}
}
