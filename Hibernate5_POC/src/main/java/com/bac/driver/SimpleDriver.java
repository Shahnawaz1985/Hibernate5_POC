package com.bac.driver;

import java.util.List;

import org.hibernate.Hibernate;

import com.bac.orm.beans.SimpleUser;
import com.bac.orm.service.UserService;
import com.bac.orm.service.impl.UserServiceImpl;

public class SimpleDriver {
	
	private static UserService userService = new UserServiceImpl();
	
	public static void main(String[] args) {		
		//getAllUsers();
		/*long startTime = System.currentTimeMillis();
		SimpleUser simpleUser = getUserById("097dd976-e25e-11e7-9b52-28d244a5b398");
		System.out.println("Total time taken in retrieving user:"+(System.currentTimeMillis() - startTime));
		//simpleUser.setLastname("Updated 12_Merged.");
		//userService.saveUser(simpleUser);
		//SimpleUser user = userService.mergeUsers("097dd976-e25e-11e7-9b52-28d244a5b398", simpleUser);
		System.out.println("Updated user, id:"+simpleUser.getId()+", updated last name:"+simpleUser.getLastname());*/
		
		long startTime1 = System.currentTimeMillis();
		SimpleUser simpleUser1 = getUserById("7aaec5dc-0815-11e8-9aba-28d244a5b398");
		System.out.println("Updated user, id:"+simpleUser1.getId()+", updated last name:"+simpleUser1.getLastname());
		System.out.println("Is user Active:"+simpleUser1.getActive());
		System.out.println("Total time taken in retrieving user1:"+(System.currentTimeMillis() - startTime1));
		
		System.out.println("------------------------------------------");
		List<SimpleUser> searchedUserList = userService.retrieveAllUsers();
		System.out.println("No of users found:"+searchedUserList.size());
		
		if(null != searchedUserList && !searchedUserList.isEmpty()) {
			for(SimpleUser user : searchedUserList) {
				Hibernate.initialize(user.getItemsForSale());
				System.out.println(user);
				System.out.println("-----------------------------------------");
			}
		}
		
		System.out.println("------------------------------------------");
		List<SimpleUser> adminUers = userService.retrieveAllUsers();
		System.out.println("No of admins found:"+adminUers.size());
		
		if(null != adminUers && !adminUers.isEmpty()) {
			for(SimpleUser user : adminUers) {
				System.out.println(user);
				System.out.println("-----------------------------------------");
			}
		}
		
		
	}
	
	private static void getAllUsers(){
		List<SimpleUser> users = userService.retrieveAllUsers();
		for(SimpleUser user : users) {
			System.out.println(user);	
			//Hibernate.initialize(user.getBillingDetails());
			System.out.println(user.getBillingDetails());
			//Hibernate.initialize(user.getDefaultBillingDetails());
			System.out.println(user.getDefaultBillingDetails());
			//Hibernate.initialize(user.getHomeAddress());
			System.out.println(user.getHomeAddress());
			//Hibernate.initialize(user.getShippingAddress());
			System.out.println(user.getShippingAddress());
		}
	}
	
	private static SimpleUser getUserById(String id) {
		SimpleUser simpleUser = userService.getUserById(id);
		System.out.println("Simple User for id "+id+" , is :"+simpleUser);
		System.out.println("Address Details for User:");
		//Hibernate.initialize(simpleUser.getBillingDetails());
		System.out.println("Billing Details :"+simpleUser.getBillingDetails());
		System.out.println("Default Billing Details for User:");
		//Hibernate.initialize(simpleUser.getDefaultBillingDetails());
		System.out.println("Default Billing Details :"+simpleUser.getDefaultBillingDetails());
		System.out.println("Home Address Details for User:");
		//Hibernate.initialize(simpleUser.getHomeAddress());
		System.out.println("Home Address :"+simpleUser.getHomeAddress());
		System.out.println("Shipping Address Details for User:");
		//Hibernate.initialize(simpleUser.getShippingAddress());
		System.out.println("Shipping Details :"+simpleUser.getShippingAddress());
		System.out.println("Creation Time :"+simpleUser.getCreatedDate());
		return simpleUser;
	}

}
