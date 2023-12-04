package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyVO;

public interface ReplyMapper {

	int insert(ReplyVO vo);
	ReplyVO read(Long rno);
	int delete(Long rno);
	int update(ReplyVO reply);
	
	// 페이징 처리가 된 댓글 목록 반환
	// @Param 의미 ? 
	// MyBatis에 넘겨줄 파라미터가 2개 이상일 떄
	//    			1) Criteria + bno > 새로운 클래스 선언
	//				2) Map 이용 > 파라미터로 넘기기 
	//				3) @Param 사용 
	List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

	int getCountByBno(Long bno);
}