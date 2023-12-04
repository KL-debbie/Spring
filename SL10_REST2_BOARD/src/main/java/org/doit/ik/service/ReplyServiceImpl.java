package org.doit.ik.service;

import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyPageDTO;
import org.doit.ik.domain.ReplyVO;
import org.doit.ik.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
//	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("register" + vo);
		return this.mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get" + rno);
		return this.mapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove" + rno);
		return this.mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify" + vo);
		return this.mapper.update(vo);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("get Reply List + Count of Board" + bno);
		return new ReplyPageDTO(this.mapper.getListWithPaging(cri, bno), this.mapper.getCountByBno(bno));
	}


}
