package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardService {

	// 1. 글 목록
	List<BoardVO> getList();

	// 2. 글 작성
	void register(BoardVO board);

	// 3. 글 보기
	BoardVO get(Long bon);

	// 4. 글 수정
	boolean modify(BoardVO board);

	// 5. 글 삭제
	boolean remove(Long bon);

	// 6. 페이지 처리 글 목록
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotal(Criteria criteria );
}
