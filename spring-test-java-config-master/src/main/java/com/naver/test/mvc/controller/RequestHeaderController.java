package com.naver.test.mvc.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mvc")
public class RequestHeaderController {
	private final Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

	/**
	 * 브라우저의 정보가 찍힘
	 * 반응형 웹을 만들때 모바일이라면 다른 화면을 보낼 수 있음
	 * 실습하면서 쓸일은 없을 듯
	 */
	@RequestMapping("/header")
	public String header(@RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Accept-Language") String acceptLanguage,
			@RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent, HttpServletResponse response) {

		logger.info("accept: " + accept);
		logger.info("acceptLanguage: " + acceptLanguage);
		logger.info("userAgent: " + userAgent);

		return "success";
	};
}
