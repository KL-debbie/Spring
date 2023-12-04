package org.doit.ik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.doit.ik.domain.SampleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample/*")
@Log4j
public class SampleController {


	// 1. 단순 문자열 반환
	// "MIME TYPE : " 해당 메서드가 생산하는 타입
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "안뇽";

	}

	// MediaType.APPLICATION_JSON_UTF8_VALUE
	// 스프링 5.2부터 변경 > MediaType.APPLICATION_JSON_VALUE
	@GetMapping(value = "/getSample", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE
			, MediaType.APPLICATION_XML_VALUE
	})
	public SampleVO getSample() {

		return new SampleVO(112,"스타","로드");
	}

	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(113,"스타","로드");
	}

	// 컬렉션 타입 객체 반환
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		list.add(new SampleVO(1,"first1","last1"));
		list.add(new SampleVO(2,"first2","last2"));
		list.add(new SampleVO(3,"first3","last3"));
		list.add(new SampleVO(4,"first4","last4"));

		return list;
	}

	
	@GetMapping(value = "/getList2")
	public List<SampleVO> getList2() {
	
		return IntStream.range(1, 10).mapToObj(i->new SampleVO(i, "first"+i, "last"+i)).collect(Collectors.toList());
	}
	
	//맵
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
	
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("first", new SampleVO(1,"first1","last1"));
		map.put("sec", new SampleVO(1,"first2","last2"));
		
		return map;
	}
	
	// ResponseEntity 타입 반환
	// Rest 방식 : 순수한 문자열, JSON, XML 데이터 송/수신
	// 정상적 데이터인지 비정상적 데이터인지 구분
	// ResponseEntity<T> = 응답 JSON 데이터 + HTTP 상태 코드 
	// 예} height 파라미터 값이 150 기준 미만 상태코드 + 값 전달
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
	
		ResponseEntity<SampleVO> result = null;
		SampleVO vo = new SampleVO(1, height+"", weight + "");
		if (height >= 150) {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}
		return result;
	}
	
//	@PathVariable
	// REST 방식 : URL 내에 최대한 많은 정보를 담으려 함
	//					?파라미터=값
	@GetMapping(value = "/product/{cat}/{pid}")
	public String [] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid ) {
	
		return new String[] {"category : " + cat, "product: " + pid};
	}
	
	//post 요청
	// {"mno" : 1, "fname" : "fn", "lname": "ln" }
	@PostMapping("/samplevo")
	public SampleVO convert(@RequestBody SampleVO vo) {
		log.info("convert "  + vo);
		return vo;
	}
}
