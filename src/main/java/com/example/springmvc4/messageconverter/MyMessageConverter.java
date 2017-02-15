package com.example.springmvc4.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.example.springmvc4.domain.DemoObj;

/**
 * HttpMessageConverter是用来处理request和response里的数据的
 * @author WQXia
 *
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj>{

	public MyMessageConverter() {
		/*自定义媒体类型*/
		super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
	}
	
	/**
	 * 表明只处理DemoObj这个类
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return DemoObj.class.isAssignableFrom(clazz);
	}

	/**
	 * 处理请求数据
	 */
	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		return new DemoObj(new Integer(tempArr[0]), tempArr[1]);
	}

	/**
	 * 输出数据到response
	 */
	@Override
	protected void writeInternal(DemoObj t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String out = "hello:" + t.getId() + "-" + t.getName();
		outputMessage.getBody().write(out.getBytes());
	}

}
