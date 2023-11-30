package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Repository
@Service
public class MemberShipServiceImp implements MemberShipService{
	
	@Autowired
	private NoticeDao dao;

	@Override
	//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED) //메서드 1개만
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		
		this.dao.insert(notice);
		
		notice.setTitle( notice.getTitle() +"-2" );
		
		this.dao.insert(notice);
	}
}
