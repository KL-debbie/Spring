package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository //자동으로 @Component 스캔 -빈 객체 생성 + DI
//@Transactional 클래스 전부
public class NoticeDaoImpl implements NoticeDao{

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

//	@Autowired
	//	private TransactionTemplate transactionTemplate;
	//	private DataSourceTransactionManager transactionManager;

	//  공지사항 총 갯수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "FROM NOTICES "
				+ "WHERE "+field+" LIKE :query";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("query", "%" + query +"%"); // ? 대신 

		return this.npJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);

	}

	// 공지사항 목록
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					

		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...

		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT ROWNUM NUM, N.* "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM NOTICES "
				+ "WHERE "+field+" LIKE :query "
				+ "ORDER BY REGDATE DESC "
				+ ") N "
				+ " ) "
				+ "WHERE NUM BETWEEN :srow AND :erow ";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%" + query + "%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}

	// 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{

		String sql = "DELETE FROM NOTICES "
				+ "WHERE SEQ= :seq";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);

		return this.npJdbcTemplate.update(sql, parameterSource);
	}

	// 공지사항 수정
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{


		String sql = "UPDATE NOTICES "
				+ "SET TITLE= :title, CONTENT= :content, FILESRC= :filesrc "
				+ "WHERE SEQ= :seq";

		/*
	      MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	      parameterSource.addValue("title", notice.getTitle());
	      parameterSource.addValue("content", notice.getContent());
	      parameterSource.addValue("filesrc",notice.getFilesrc());
	      parameterSource.addValue("seq", notice.getSeq() );

	      return this.npJdbcTemplate.update(sql, parameterSource);
		 */

		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

		return this.npJdbcTemplate.update(sql,parameterSource );
	}

	// 공지사항 조회
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "FROM NOTICES "
				+ "WHERE SEQ = :seq ";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);

		return this.npJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class) );
	}

	// 공지사항 추가
	public int insert(NoticeVO no) throws ClassNotFoundException, SQLException {

		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(no);

		return this.npJdbcTemplate.update(sql,parameterSource );
	}

	// 1] 트랜잭션 처리가 안된 메서드
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

		this.npJdbcTemplate.update(sql,parameterSource );
		// 작성자 포인트 증가
		sql = "UPDATE member"
				+ " SET point = point +1 "
				+ " WHERE id = :id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		int updatecnt = this.npJdbcTemplate.update(sql, paramSource);
	}
	 */

	// 2] 트랜잭션 처리 메서드
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		String sql2 = "UPDATE member"
				+ " SET point = point +1 "
				+ " WHERE id = :id";

		//commit시..다른 트랜잭션과 충돌 될 경우 어떻게 트랜잭션 처리할지
		// 격리성 관련 설정 상태
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(definition );

		try {

			// a
			SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

			this.npJdbcTemplate.update(sql,parameterSource );

			//b 
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("id", id);

			int updatecnt = this.npJdbcTemplate.update(sql2, paramSource);

			// commit
			this.transactionManager.commit(status);

		} catch (Exception e) {
			// rollback
			this.transactionManager.rollback(status);
		}

	}
	 */

	// 3] 
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		String sql2 = "UPDATE member"
				+ " SET point = point +1 "
				+ " WHERE id = :id";

		// p514
																				WithoutResult() : 리턴할 결과값이 없는 경우
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// a
				SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

				npJdbcTemplate.update(sql,parameterSource );

				//b 
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("id", id);

				int updatecnt = npJdbcTemplate.update(sql2, paramSource);
			}
		});
	 */

	// 4] 선언적 트랜잭션
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		// a
		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

		npJdbcTemplate.update(sql,parameterSource );

		String sql2 = "UPDATE member"
				+ " SET point = point +1 "
				+ " WHERE id = :id";
		//b 
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		int updatecnt = npJdbcTemplate.update(sql2, paramSource);
*/

	// 5 @ 트랜잭션
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED) //메서드 1개만
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 공지사항 쓰기
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ))+1, 1) FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";

		// a
		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(notice);

		npJdbcTemplate.update(sql,parameterSource );

		String sql2 = "UPDATE member"
				+ " SET point = point +1 "
				+ " WHERE id = :id";
		//b 
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		int updatecnt = npJdbcTemplate.update(sql2, paramSource);


	}

}
