package com.naver.test.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.test.mvc.model.HelloParam;

/**
 * RequestMapping 및 RequestParam 테스트
 * 
 * @author nhn
 *
 */
@Controller
@RequestMapping("/mvc")
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	
	/**
	 * 같은 경로라도 매서드 종류에 따라 다르게 메소드를 호출함
	 */
	@RequestMapping(value = "/hello", method = { RequestMethod.GET })
	public String helloGet(Model model,
			@RequestParam(value = "message", required = false, defaultValue = "test") String message) {
		logger.info("this is hello controller : http method(GET)");
		model.addAttribute("message", message);

		return "showMessage";
	};

	@RequestMapping(value = "/hello", method = { RequestMethod.POST })
	public String helloPost(Model model, @RequestParam String message) {
		logger.info("this is hello controller : http method(POST)");
		model.addAttribute("message", message);
		return "showMessage";
	};

	/**
	 * 배열 형태로 여러개 호출도 가능
	 */
	@RequestMapping(value = { "hello1", "hello2" })
	public String hellos(Model model, @RequestParam String message) {
		logger.info("this is hello controller : http method(GET)");
		model.addAttribute("message", message);
		return "showMessage";
	};

	/**
	 * 파라미터에 ModelAttribute 어노테이션 생략 가능
	 * 그리고 뷰에서 값을 받아올때 객체 자체로 받아옴
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping("hello3")
	public String hello3(Model model, HelloParam param) {
		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return "showMessage";
	};

	/**
	 * @ModelAttribute 를 생략해서 쓰는 경우가 많다
	 */
	@RequestMapping("hello4")
	public String hello4(Model model, @ModelAttribute HelloParam param) {
		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return "showMessage";
	};
	/**
	 * 이것 보다는 헬로6이 더 좋음
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping("hello5")
	public String hello5(Model model, HelloParam param) {
		model.addAttribute("message", param.getMessage());

		logger.info("modelAttribute : " + model.asMap().toString());
		return "showMessage";
	};

	/**
	 * RequestMapping보다 먼저 수행된다.
	 * 컨트롤러가 맵핑된 다른 메소드들 보다 먼저 수행
	 * 세팅하기에 앞서서 
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("msg", "Welcome to My Country");
	}

	/**
	 * 명시적으로 Model & View 리턴
	 * 헬로5랑 동작이 같음
	 * 둘중에 헬로5가 더 짧고 읽기 좋음
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping("hello6")
	public ModelAndView hello6(Model model, HelloParam param) {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("showMessage");

		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return mov;
	};
}
