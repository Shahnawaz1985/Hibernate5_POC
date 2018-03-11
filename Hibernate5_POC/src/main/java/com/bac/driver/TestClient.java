package com.bac.driver;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bac.orm.beans.Address;
import com.bac.orm.beans.AddressEntity;
import com.bac.orm.beans.BankAccount;
import com.bac.orm.beans.BillingDetails;
import com.bac.orm.beans.CreditCard;
import com.bac.orm.beans.CreditCardType;
import com.bac.orm.beans.Item;
import com.bac.orm.beans.SimpleUser;
import com.bac.orm.config.CreditCardNumberGenerator;
import com.bac.orm.config.MonetaryAmount;
import com.bac.orm.constants.PaymentConstants;
import com.bac.orm.service.UserService;
import com.bac.orm.service.impl.UserServiceImpl;
import com.bac.orm.util.HibernateUtil;

public class TestClient {
	
	private static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.createSessionFactory();
		Session session = null;
		Transaction tx = null;
		List<SimpleUser> userList = null;
		/*if (null != sf) {
			System.out.println("Statistics" + sf.getStatistics());
		}*/

		SimpleUser user = createSimpleUser();
		Item item = getItemForSale(user);
		if (null != sf) {
			session = sf.getCurrentSession();
			if(null != session) {
				tx = session.beginTransaction();
		user.getItemsForSale().add(item);
		System.out.println("Saving User:" + user);
		session.saveOrUpdate(user);
		//System.out.println("Saving Item:"+item);
		//session.saveOrUpdate(item);
		// tx.commit();
		session.flush();

		/*SimpleUser user1 = (SimpleUser) session.load(SimpleUser.class, user.getId());
		if (null != user1) {
			System.out.println(user1);
			user1.setEmail("shahnawazakhter@live.com");
		}*/
		
		userList = session.createQuery("from SimpleUser").list();
		System.out.println("No of Users :" + userList.size() + " , Users Info :");
		System.out.println(userList);
		tx.commit();
			}
		}		
		System.out.println("Users Address:");
		for(SimpleUser simpleUser : userList) {
			System.out.println("Address Details for "+simpleUser.getUsername());
			System.out.println("Home Address :"+simpleUser.getHomeAddress());
			System.out.println("Billing Address :"+simpleUser.getBillingAddress());
			System.out.println("Default Billing Address :"+simpleUser.getDefaultBillingDetails());
			System.out.println("Shipping Address :"+simpleUser.getShippingAddress());
		}
		
		if (null != session && session.isOpen()) {
			session.close();
		}
	}

	private static SimpleUser createSimpleUser() {
		SimpleUser user = new SimpleUser();
		user.setFirstname("Shahnawaz");
		user.setLastname("Akhter "+new Random().nextInt(100));
		user.setPassword("password@"+new Random().nextInt(100));
		user.setEmail("shahnawaz.akhter@bofa.com");
		user.setUsername("user"+new Random().nextInt(100));
		user.setHomeAddress(createhomeAddress());
		user.setBillingAddress(createBillingAddress());
		user.setShippingAddress(createshippingAddress(user));
		user.setDefaultBillingDetails(getDefaultBillingDetails(user));
		user.addBillingDetails(getDefaultBillingDetails(user));
		user.addBillingDetails(getBillingDetailsBA(user));
		user.setAdmin("Y");
		user.setActive("Y");
		user.setCreatedDate(new java.sql.Timestamp( new Date().getTime()));
		return user;
	}
	
	private static Address createhomeAddress() {
		Address address = new Address();
		address.setCity("Gurgaon");
		address.setStreet("MG Road - " + new Random().nextInt(1000));
		address.setZipcode("1200" + new Random().nextInt(99));
		return address;
	}
	
	private static Address createBillingAddress() {
		Address address = new Address();
		address.setCity("New Delhi");
		address.setStreet("New Friends Colony - " + new Random().nextInt(1000));
		address.setZipcode("1100" + new Random().nextInt(99));
		return address;
	}
	
	private static AddressEntity createshippingAddress(SimpleUser user) {
		AddressEntity addr = new AddressEntity();
		addr.setCity("Gurgaon");
		addr.setStreet("Cybercity - " + new Random().nextInt(1000));
		addr.setZipcode("1220" + new Random().nextInt(99));
		addr.setSimpleUser(user);
		return addr;
	}
	
	private static BillingDetails getDefaultBillingDetails(SimpleUser user) {
		//RandomGUID myGUID = new RandomGUID();
		CreditCardNumberGenerator ccgenerator = new CreditCardNumberGenerator();
		String cc_number = ccgenerator.generate("8001", 11);
		//String cc_number = myGUID.toString();
		BillingDetails bd = new CreditCard(user.getUsername(), user, cc_number, CreditCardType.AMEX,
				"AMEX", "11", "2022");
		return bd;
	}
	
	private static BillingDetails getBillingDetailsBA(SimpleUser user) {
		//RandomGUID myGUID = new RandomGUID();
		CreditCardNumberGenerator ccgenerator = new CreditCardNumberGenerator();
		String accountNumber = ccgenerator.generate("1007", 12);
		//String cc_number = myGUID.toString();
		BillingDetails bd = new BankAccount(user.getUsername(), user, accountNumber, PaymentConstants.AXIS_BANK, PaymentConstants.SWIFT_ACS);
		return bd;
	}
	
	private static Item getItemForSale(SimpleUser user) {
		//SimpleUser seller = userService.getUserById("9cfb9430-0816-11e8-9aba-28d244a5b398");
		//SimpleUser buyer = userService.getUserById("d7aa5262-0815-11e8-9aba-28d244a5b398");
		String description = "Product A for sale.";
		String name = "Product A";
		
		MonetaryAmount initialPrice = new MonetaryAmount(new BigDecimal(100), Currency.getInstance("USD"));
		MonetaryAmount reservedPrice = new MonetaryAmount(new BigDecimal(1000), Currency.getInstance("USD"));
		java.sql.Date startDate = new java.sql.Date(new java.util.Date().getTime());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 4);
		java.util.Date targetEndDate = c.getTime();
		java.sql.Timestamp endDate = new java.sql.Timestamp(new java.util.Date().getTime() + targetEndDate.getTime());
		Item itemA = new Item(name, description, user, initialPrice,
				reservedPrice, endDate);			
		return itemA;
		}

}
