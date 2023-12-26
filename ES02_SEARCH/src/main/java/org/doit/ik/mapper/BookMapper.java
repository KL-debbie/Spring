package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BookDTO;

public interface BookMapper {

	// 조회
	List<BookDTO> list();
	
	// book
	BookDTO get(String seq);

	// 추가
	void add(BookDTO dto);

}

