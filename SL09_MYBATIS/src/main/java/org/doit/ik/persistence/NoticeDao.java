package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.transaction.annotation.Transactional;

//@Transactional 인터페이스 전부
public interface NoticeDao {
	
	//  공지사항 총 갯수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 목록
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 수정
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// 공지사항 조회
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 추가
	public int insert(NoticeVO no) throws ClassNotFoundException, SQLException ;
	
	// 트랜잭션 처리하기 위한 메서드 추가
//	@Transactional 해당 메서드만
	// void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException ;
	
	void hitup(String seq);
	int getHit(String seq);
}
