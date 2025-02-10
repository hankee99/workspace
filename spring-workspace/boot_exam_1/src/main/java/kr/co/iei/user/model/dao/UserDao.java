package kr.co.iei.user.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import kr.co.iei.user.model.dto.UserDTO;

import kr.co.iei.user.model.dto.UserRowMapper;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private UserRowMapper userRowMapper;

	public UserDTO searchUser(UserDTO user) {

		String query = "select * from user_tbl where user_name = ?";

		Object[] params = {user.getUserName()};

		List list = jdbc.query(query, userRowMapper, params);
		System.out.println(user.getUserName());
		System.out.println(list.size());
		if(list.isEmpty()) {
			return null;
		}else {
			return (UserDTO) (list.get(0));
		}
		
		

	}

}