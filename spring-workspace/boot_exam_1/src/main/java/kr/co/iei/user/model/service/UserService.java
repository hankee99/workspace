package kr.co.iei.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import kr.co.iei.user.model.dao.UserDao;

import kr.co.iei.user.model.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserDTO searchUser(UserDTO u) {

		UserDTO user = userDao.searchUser(u);

		return user;

	}

}