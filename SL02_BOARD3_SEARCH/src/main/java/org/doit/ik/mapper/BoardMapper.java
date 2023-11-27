package org.doit.ik.mapper;

import java.util.List;
import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {

	// 1. 글 목록
	List<BoardVO> getList();
	
	// 2. 글 작성
	void insert(BoardVO board);
	void insertSelectKey(BoardVO board);
		
	// 3. 글 보기
	BoardVO read(Long bon);
	
	// 4. 글 수정
	int update(BoardVO board);
	
	// 5. 글 삭제
	int delete(Long bon);
	
	// 6. 페이지 처리 글 목록
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotalCount(Criteria criteria );

}