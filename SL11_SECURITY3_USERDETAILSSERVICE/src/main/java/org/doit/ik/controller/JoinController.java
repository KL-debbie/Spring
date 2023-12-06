package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = "/joinus/*")
public class JoinController {
	
	@Autowired
	private MemberMapper memberDao; 
	
	@GetMapping("/login.htm")
	public String login() throws Exception{
		return "joinus.login";
	}
	
	@GetMapping("/join.htm")
	public String join() throws Exception{
		return "joinus.join";
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	@PostMapping("/join.htm")
	public String join(MemberVO member) throws Exception{
		String pwd = member.getPwd();
		member.setPwd(	this.passwordEncoder.encode(pwd) );
		this.memberDao.insert(member);
		return "redirect:../index.htm";
	}
	
	
}
