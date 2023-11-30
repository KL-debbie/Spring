package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	//회원정보 반환
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				+ "FROM MEMBER "
				+ "WHERE id = :id ";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);

		return this.npJdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
	}

	public int insert(MemberVO member) throws ClassNotFoundException, SQLException{
		String sql = "INSERT INTO MEMBER( ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) "
						+ "VALUES( :id, :pwd, :name, :gender, :birth, :is_lunar, :cphone, :email, :habit, SYSDATE)";

		SqlParameterSource parameterSource =new BeanPropertySqlParameterSource(member);

		return this.npJdbcTemplate.update(sql,parameterSource );
	}
}