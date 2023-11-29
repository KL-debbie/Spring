package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/joinus/*")
public class JoinController {
	
	@Autowired
	private MemberDao memberDao; 
	
	@GetMapping("/login.htm")
	public String login() throws Exception{
		return "login.jsp";
	}
	
	@GetMapping("/join.htm")
	public String join() throws Exception{
		return "join.jsp";
	}

	@PostMapping("/join.htm")
	public String join(MemberVO member) throws Exception{
		this.memberDao.insert(member);
		return "redirect:../index.htm";
	}
	
	
}
