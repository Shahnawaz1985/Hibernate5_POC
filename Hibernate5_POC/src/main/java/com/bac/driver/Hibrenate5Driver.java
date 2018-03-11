package com.bac.driver;

import com.bac.orm.dao.UserDAO;
import com.bac.orm.dao.impl.UserDAOImpl;

import java.util.List;

import com.bac.orm.beans.SimpleUser;

public class Hibrenate5Driver {
	
	public static void main(String[] args) {
		UserDAO userDao = new UserDAOImpl();
		String[] userids = new String[] {"d6e0723d-2524-11e8-aafe-28d244a5b398", 
				"dcd20e5d-2524-11e8-aafe-28d244a5b398", "402881ea6214fe61016214fe6d340000"};
		List<SimpleUser> users = userDao.findMultipleUsers(userids);
		System.out.println("Multiple users:"+users);
		System.out.println("Multiple Users fetched.");
	}

}
