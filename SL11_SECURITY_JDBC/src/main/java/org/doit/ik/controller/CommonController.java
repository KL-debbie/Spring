package org.doit.ik.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/common/*")
public class CommonController {

	@GetMapping("/accessError.htm")
	public String accessDenied(Model model, Authentication auth) throws Exception{
		log.info(">/common/accessError.htm get");
		model.addAttribute("msg","Access Denied");
		return "/common/accessError";
	}
	
}
