package org.doit.ik.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	// member 테이블의 컬럼명가 필드명 일치
	// 필드
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar;
	private String cphone;
	private String email;
	private String habit;
	private Date   regdate;
	
	
}
