package com.bac.orm.beans;

import java.io.Serializable;
import java.util.Date;

public class CategorizedItemComponent implements Comparable<CategorizedItemComponent>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3550456331063327391L;
	
	private String username; // This could also be a many-to-one association to User
    private Date dateAdded = new Date();
    private Item item;
    private Category category;
    
    

	public CategorizedItemComponent() {
		super();		
	}
	
	public CategorizedItemComponent(String username, Item item, Category category) {
		super();
		this.username = username;
		this.item = item;
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	public Date getDateAdded() {
		return new Date(dateAdded.getTime());
	}

	public Item getItem() {
		return item;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		CategorizedItemComponent other = (CategorizedItemComponent) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
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
		builder.append("CategorizedItemComponent [username=").append(username).append(", dateAdded=").append(dateAdded)
				.append(", item=").append(item).append(", category=").append(category).append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(CategorizedItemComponent o) {
		return getDateAdded().compareTo( ((CategorizedItemComponent)o).getDateAdded() );
	}

}
