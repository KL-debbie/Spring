package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;


public interface MemberMapper {
	
	//회원정보 반환
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	//회원가입
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
	//회원 + 권한 정보 얻어오기
	public MemberVO read(@Param("userid") String userid);
}
