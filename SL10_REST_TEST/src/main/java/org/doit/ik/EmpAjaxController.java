package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class EmpAjaxController {
	
	//@Autowired
	@Setter(onMethod=@__({@Autowired}))
	private MemberMapper mapper;
	
	@GetMapping(value = "/Emp/{deptno}")
	public ResponseEntity<List<EmpDTO>> selDept (
			@PathVariable("deptno") int deptno){
		log.info("> getEmpList... " + deptno );
		return new ResponseEntity<>( this.mapper.select(deptno), HttpStatus.OK);		
	}
	


	
	}
