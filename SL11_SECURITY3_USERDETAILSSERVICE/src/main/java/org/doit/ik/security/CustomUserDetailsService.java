package org.doit.ik.security;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.doit.ik.service.domain.CustomerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Component("customUserDetailsService")
@Log4j
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(" loadUserBy Username : " + username);
		MemberVO vo= this.mapper.read(username);
		log.warn(" MemberMapper : " + vo);
		
		// MemberVO vo > UserDetails 타입 형 변환
		// CustomerUser extends User implements UserDetails
		
		return vo == null ? null : new CustomerUser(vo);
	}
	
}
