package org.doit.ik.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	String getTime();
	
	@Select("select sysdate + 1 from dual")
	String getNextTime();
	
}
