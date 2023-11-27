package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {

	ArrayList<DeptDTO> selectDept();
	int insertDept(DeptDTO dto);
	int deleteDept(int deptno);
}
