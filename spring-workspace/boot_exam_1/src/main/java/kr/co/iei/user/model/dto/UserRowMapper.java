package kr.co.iei.user.model.dto;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<UserDTO> {
	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserDTO user = new UserDTO();

		user.setBloodType(rs.getString("blood_type"));

		user.setUserAge(rs.getInt("user_age"));

		user.setUserGender(rs.getString("user_gender"));

		user.setUserName(rs.getString("user_name"));

		user.setUserNo(rs.getInt("user_no"));

		return user;

	}

}