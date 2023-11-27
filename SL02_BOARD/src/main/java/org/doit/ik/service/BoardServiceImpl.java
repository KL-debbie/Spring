package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor  // 스프링 4.3부터 생성자 DI에 의해 자동으로 객체 주입됨
@Log4j
public class BoardServiceImpl implements BoardService{

//	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("> BoardServiceImpl.getList ");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO board) {
		log.info("> BoardServiceImpl.register ");
//		this.boardMapper.insert(board);
		this.boardMapper.insertSelectKey(board);
		
	}

	@Override
	public BoardVO get(Long bon) {
		log.info("> BoardServiceImpl.read ");
		return this.boardMapper.read(bon);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("> BoardServiceImpl.modify ");
		return this.boardMapper.update(board)==1;
	}

	@Override
	public boolean remove(Long bon) {
		log.info("> BoardServiceImpl.remove ");
		return this.boardMapper.delete(bon)==1;
	}



}
