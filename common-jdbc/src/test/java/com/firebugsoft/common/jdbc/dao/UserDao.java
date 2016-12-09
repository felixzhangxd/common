package com.firebugsoft.common.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.firebugsoft.common.jdbc.po.UserPo;

@Repository
public class UserDao {
	private Logger logger = LoggerFactory.getLogger("sql");
	private RowMapper<UserPo> mapper = BeanPropertyRowMapper.newInstance(UserPo.class);
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void save(final UserPo po) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "insert into t_user (name) values(?)";
				logger.info("{}; {}", sql, po);
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, po.getName());
				return ps;
			}
		}, keyHolder);
		po.setId(keyHolder.getKey().intValue());
	}

	public int removeById(Integer id) {
		String sql = "delete from t_user where id=?";
		Object[] args = new Object[] { id};
		logger.info("{}; {}", sql, args);
		return jdbcTemplate.update(sql, args);
	}

	public int modifyById(UserPo po) {
		String sql = "update t_user set name=? where id=?";
		Object[] args = new Object[] {po.getName(), po.getId()};
		logger.info("{}; {}", sql, args);
		return jdbcTemplate.update(sql, args);
	}

	public UserPo findById(Integer id) {
		String sql = "select id,name from t_user where id=?";
		Object[] args = new Object[] {id};
		logger.info("{}; {}", sql, args);
		return jdbcTemplate.queryForObject(sql, args, mapper);
	}
}
