package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class HomeAjaxController {
	
	//@Autowired
	@Setter(onMethod=@__({@Autowired}))
	private MemberMapper mapper;
	
//	@GetMapping("/idCheck")
//	public EmpVO idCheck(String empno) {
//		log.info("> idCheck  get-ajax " + empno);
//		int result= this.mapper.idCheck(empno);  ///1이면 중복
//		return new EmpVO(empno, "ms", result);
//	}
	
	@PostMapping("/scott/dept/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto) {
		log.info("> /scott/dept/new post");
		int result = this.mapper.insertDept(dto);
	//	응답 결과물 + http상태코드
		return result == 1? new ResponseEntity<String>("SUCCESS", HttpStatus.OK) 
						: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@DeleteMapping(value = "/scott/dept/{deptno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable("deptno")  int deptno) {
		log.info("> /scott/dept/"+ deptno +"DELETE");
		int result = this.mapper.delete(deptno);
		//	응답 결과물 + http상태코드
		return result == 1? new ResponseEntity<String>("SUCCESS", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@RequestMapping(value="/deptEmp", method = RequestMethod.GET)
	public ModelAndView select(@RequestParam(value = "deptno",defaultValue = "10")int deptno,  Model model) {
		log.info("Welcome home! The client locale is {}.");
			
		List<EmpDTO> list = this.mapper.select(deptno);
		List<DeptDTO> list2 = this.mapper.selDept();
		String viewName = "deptEmp";
		ModelAndView mv = new ModelAndView(viewName);
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		return mv; 
	}

	

	/*
	@PostMapping("/scott/dept/del")
	public int delete(int deptno) {
		log.info("> deptno DELETE");
		return this.mapper.delete(deptno);
	}
	*/
	
	
	/*
	 * @GetMapping("/idCheck") public int idCheck(String empno) {
	 * log.info("> idCheck  get-ajax " + empno); return this.mapper.idCheck(empno);
	 * ///1이면 중복
	 *  }
	 */
	
	}
