package org.doit.ik.mapper.scott;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {

	List<EmpDTO> selectEmp(ArrayList<Integer> deptnos);
}
