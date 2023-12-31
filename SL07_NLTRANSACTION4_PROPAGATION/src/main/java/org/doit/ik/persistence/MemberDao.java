package org.doit.ik.persistence;

import java.sql.SQLException;
import org.doit.ik.domain.MemberVO;


public interface MemberDao {
	
	//회원정보 반환
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	//회원가입
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
}
