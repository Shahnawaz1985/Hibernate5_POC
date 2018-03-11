package com.bac.orm.beans;

import java.io.Serializable;

public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3940660100114956942L;

	private String id = null;

	private String street;
	private String zipcode;
	private String city;

	private SimpleUser simpleUser;

	public AddressEntity() {
		super();
	}

	public AddressEntity(String street, String zipcode, String city) {
		super();
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public SimpleUser getSimpleUser() {
		return simpleUser;
	}

	public void setSimpleUser(SimpleUser simpleUser) {
		this.simpleUser = simpleUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		AddressEntity other = (AddressEntity) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"addressEntity\": [\"id\":\"").append(id).append("\", \"street\":\"").append(street).append("\", \"zipcode\":\"")
				.append(zipcode).append("\", \"city\":\"").append(city).append("\"]");
		return builder.toString();
	}

}
