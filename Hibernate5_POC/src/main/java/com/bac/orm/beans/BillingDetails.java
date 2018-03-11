package com.bac.orm.beans;

import java.io.Serializable;
import java.util.Date;

public abstract class BillingDetails implements Serializable, Comparable<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2788179644789039501L;
	private Long id = null;
	private String owner;
	private SimpleUser user;
	private Date created = new Date();
	
	
	public BillingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BillingDetails(String owner, SimpleUser user) {
		super();
		this.owner = owner;
		this.user = user;
	}

	public Long getId() {
		return id;
	}	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public SimpleUser getUser() {
		return user;
	}
	
	public Date getCreated() {
		return new Date(created.getTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
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
		BillingDetails other = (BillingDetails) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"billingDetails\": [\"id\":\"").append(id).append("\", \"owner\":\"").append(owner)
				.append("\", \"created\":\"").append(created).append("\"]");
		return builder.toString();
	}

	public int compareTo(Object o) {
		return Long.compare(this.getCreated().getTime(), ((BillingDetails) o).getCreated().getTime());
	}
	

}
