package com.bac.orm.service.impl;

import java.util.List;

import com.bac.orm.beans.SimpleUser;
import com.bac.orm.dao.UserDAO;
import com.bac.orm.dao.impl.UserDAOImpl;
import com.bac.orm.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}

	@Override
	public List<SimpleUser> retrieveAllUsers() {
		return userDAO.retrieveAllUsers();
	}

	@Override
	public void saveUser(SimpleUser user) {
		userDAO.saveUser(user);
	}

	@Override
	public SimpleUser getUserById(String id) {
		return userDAO.getUserById(id);
	}

	@Override
	public SimpleUser mergeUsers(String id, SimpleUser user) {
		return userDAO.mergeObjects(id, user);
	}

	@Override
	public List<SimpleUser> searchUserWithFirstName(String firstName) {
		SimpleUser user = new SimpleUser();
		user.setFirstname(firstName);
		return userDAO.findUserByFirstname(user);
	}

	@Override
	public List<SimpleUser> retrieveAdminUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
