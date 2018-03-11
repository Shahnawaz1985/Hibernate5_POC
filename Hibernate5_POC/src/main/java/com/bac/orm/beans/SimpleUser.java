package com.bac.orm.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.bac.exception.BusinessException;

public class SimpleUser implements Serializable, Comparable<SimpleUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8866210083514991077L;

	private String id = null;

	private String firstname;
	private String lastname;
	private String username; // Unique and immutable
	private String password;
	private String email;

	private Address homeAddress;
	private AddressEntity shippingAddress;
	private Address billingAddress;

	private Set<BillingDetails> billingDetails = new HashSet<BillingDetails>();
	private BillingDetails defaultBillingDetails;

	private Collection<Item> itemsForSale = new ArrayList<Item>();
	private Set<Item> boughtItems = new HashSet<Item>();

	private String admin;
	
	private String active;

	private Timestamp  createdDate;	

	public void setCreatedDate(Timestamp  createdDate) {
		this.createdDate = createdDate;
	}
	
	public SimpleUser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Full constructor
	 */
	public SimpleUser(String firstname, String lastname, String username, String password, String email, 
			String admin, Address homeAddress, Address billingAddress, AddressEntity shippingAddress,
			Set<BillingDetails> billingDetails, BillingDetails defaultBillingDetails, Set<Item> itemsForSale,
			Set<Item> boughtItems) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.admin = admin;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.billingDetails = billingDetails;
		this.defaultBillingDetails = defaultBillingDetails;
		this.itemsForSale = itemsForSale;
		this.boughtItems = boughtItems;
	}

	/**
	 * Simple constructor.
	 */
	public SimpleUser(String firstname, String lastname, String username, String password, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public AddressEntity getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressEntity shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Set<BillingDetails> getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(Set<BillingDetails> billingDetails) {
		this.billingDetails = billingDetails;
	}

	public BillingDetails getDefaultBillingDetails() {
		return defaultBillingDetails;
	}

	public void setDefaultBillingDetails(BillingDetails defaultBillingDetails) {
		this.defaultBillingDetails = defaultBillingDetails;
	}

	public Collection<Item> getItemsForSale() {
		return itemsForSale;
	}

	public void setItemsForSale(Collection<Item> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}

	public Set<Item> getBoughtItems() {
		return boughtItems;
	}

	public void addBoughtItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Null Item!");
		item.setBuyer(this);
		boughtItems.add(item);
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * Adds a <tt>BillingDetails</tt> to the set.
	 * <p>
	 * This method checks if there is only one billing method in the set, then makes
	 * this the default.
	 *
	 * @param billingDetails
	 */
	public void addBillingDetails(BillingDetails billingDetails) {
		if (billingDetails == null)
			throw new IllegalArgumentException("Can't add a null BillingDetails.");
		this.getBillingDetails().add(billingDetails);

		if (getBillingDetails().size() == 1) {
			setDefaultBillingDetails(billingDetails);
		}
	}

	/**
	 * Removes a <tt>BillingDetails</tt> from the set.
	 * <p>
	 * This method checks if the removed is the default element, and will throw a
	 * BusinessException if there is more than one left to chose from. This might
	 * actually not be the best way to handle this situation.
	 *
	 * @param billingDetails
	 * @throws BusinessException
	 */
	public void removeBillingDetails(BillingDetails billingDetails) throws BusinessException {
		if (billingDetails == null)
			throw new IllegalArgumentException("Can't add a null BillingDetails.");

		if (getBillingDetails().size() >= 2) {
			getBillingDetails().remove(billingDetails);
			setDefaultBillingDetails((BillingDetails) getBillingDetails().iterator().next());
		} else {
			throw new BusinessException("Please set new default BillingDetails first");
		}
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleUser other = (SimpleUser) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"simpleUser\": [\"id\":\"").append(id).append("\", \"firstname\":\"").append(firstname).append("\", \"lastname\":")
				.append(lastname).append("\", \"username\":\"").append(username).append("\", \"password\":\"").append(password)
				.append("\", \"email\":\"").append(email).append("\", \"homeAddress\":\"").append(homeAddress)
				.append("\", \"shippingAddress\":\"").append(shippingAddress).append("\", \"billingAddress\":\"").append(billingAddress)
				.append("\", \"billingDetails\":\"").append(billingDetails).append("\", \"defaultBillingDetails\":\"")
				.append(defaultBillingDetails).append("\", \"itemsForSale\":\"").append(itemsForSale).append("\", \"boughtItems\":\"")
				.append(boughtItems).append("\", \"admin\":\"").append(admin).append("\", \"created\":\"").append(createdDate).append("\", \"active\":\"").append(active).append("\"]");
		return builder.toString();
	}

	@Override
	public int compareTo(SimpleUser o) {
		return this.getUsername().compareTo(o.getUsername());
	}

}
