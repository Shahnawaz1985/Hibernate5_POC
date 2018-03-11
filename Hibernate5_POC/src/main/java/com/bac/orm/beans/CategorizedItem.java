package com.bac.orm.beans;

import java.io.Serializable;
import java.util.Date;

public class CategorizedItem implements Comparable<CategorizedItem>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2020925206372366932L;
	
	/**
     * Emedded composite identifier class that represents the
     * primary key columns of the many-to-many join table.
     */
    public static class Id implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3329095489603900247L;
		private Long categoryId;
		private Long itemId;

        public Id() {}

        public Id(Long categoryId, Long itemId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
		}

		public boolean equals(Object o) {
			if (o instanceof Id) {
				Id that = (Id)o;
				return this.categoryId.equals(that.categoryId) &&
					   this.itemId.equals(that.itemId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return categoryId.hashCode() + itemId.hashCode();
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Id [categoryId=").append(categoryId).append(", itemId=").append(itemId).append("]");
			return builder.toString();
		}
	}
    
    private Id id;
	private String username; // This could also be a many-to-one association to User
	private Date dateAdded = new Date();
	private Item item;
	private Category category;
	
	public CategorizedItem() {
		super();		
	}
	
	public CategorizedItem(String username, Item item, Category category) {
		this.username = username;
		this.item = item;
		this.category = category;
		
		// Set primary key
        this.id = new Id(category.getId(), item.getId());

		// Guarantee referential integrity
		category.getCategorizedItems().add(this);
		item.getCategorizedItems().add(this);
	}

	public Id getId() {
		return id;
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

	public Category getCategory() {
		return category;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CategorizedItem other = (CategorizedItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(CategorizedItem o) {
		return getDateAdded().compareTo( ((CategorizedItem)o).getDateAdded() );
	}

	@Override
	public String toString() {
		return "CategorizedItem [id=" + id + ", username=" + username + ", dateAdded=" + dateAdded + ", item=" + item
				+ ", category=" + category + "]";
	}	
}
