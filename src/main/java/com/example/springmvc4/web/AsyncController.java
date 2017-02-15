package com.example.springmvc4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.springmvc4.service.PushService;

/**
 * 服务器端推送技术（异步支持跨浏览器）
 * @author WQXia
 *
 */
@Controller
public class AsyncController {

	@Autowired
	private PushService pushService;
	
	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall() {
		return pushService.getAsyncUpdate();
	}
}
