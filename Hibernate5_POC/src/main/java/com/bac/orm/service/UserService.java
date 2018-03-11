package com.bac.orm.service;

import java.util.List;

import com.bac.orm.beans.SimpleUser;



public interface UserService {
	
	List<SimpleUser> retrieveAllUsers();
	
	List<SimpleUser> retrieveAdminUsers();
	
	void saveUser(SimpleUser user);

	SimpleUser getUserById(String id);
	
	SimpleUser mergeUsers(String id, SimpleUser user);

	List<SimpleUser> searchUserWithFirstName(String firstName);
}
