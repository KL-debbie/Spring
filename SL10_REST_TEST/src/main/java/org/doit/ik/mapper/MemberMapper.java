package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;

public interface MemberMapper {

	int idCheck(String empno);
	
	int insertDept(DeptDTO dto);
	
	int delete(int deptno);
	
	List<EmpDTO> select(int deptno);
	
	List<DeptDTO> selDept();
	
}
