package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller //일반적인 웹의 응답(JSP)을 하기 위한 Controller
public class ViewController {

	@RequestMapping(value="/users", method= {GET, POST})
	public String index() {
		return "index";
	}
}
