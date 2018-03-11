package com.bac.orm.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Category implements Comparable<Category>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4616014668044789334L;

	private Long id = null;
	private String name;
	private List<Category> childCategories = new ArrayList<Category>(); // A bag with SQL ORDER BY
	private Category parentCategory;

	private List<Item> items = new ArrayList<Item>();
	private Set<CategorizedItem> categorizedItems = new HashSet<CategorizedItem>();
	private Set<CategorizedItemComponent> categorizedItemComponents = new HashSet<CategorizedItemComponent>();
	private Map<Item, SimpleUser> itemsAndUser = new HashMap<Item, SimpleUser>();

	private Date created = new Date();

	public Category() {
		super();
	}

	public Category(String name, List<Category> childCategories, Category parentCategory, List<Item> items,
			Set<CategorizedItem> categorizedItems, Set<CategorizedItemComponent> categorizedItemComponents,
			Map<Item, SimpleUser> itemsAndUser) {
		super();
		this.name = name;
		this.childCategories = childCategories;
		this.parentCategory = parentCategory;
		this.items = items;
		this.categorizedItems = categorizedItems;
		this.categorizedItemComponents = categorizedItemComponents;
		this.itemsAndUser = itemsAndUser;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getChildCategories() {
		return childCategories;
	}

	public void addChildCategory(Category childCategory) {
		if (childCategory == null)
			throw new IllegalArgumentException("Null child category!");
		if (childCategory.getParentCategory() != null)
			childCategory.getParentCategory().getChildCategories().remove(childCategory);
		childCategory.setParentCategory(parentCategory);
		childCategories.add(childCategory);
	}

	public void removeChildCategory(Category childCategory) {
		if (childCategory == null)
			throw new IllegalArgumentException("Null child category!");
		childCategory.setParentCategory(null);
		childCategories.remove(childCategory);
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	private void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	// Regular many-to-many
	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Null item!");
		items.add(item);
		item.getCategories().add(this);
	}

	public void removeItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Null item!");
		items.remove(item);
		item.getCategories().remove(this);
	}

	// Many-to-many with additional columns on join table, intermediate entity class
	// To create a link, instantiate a CategorizedItem with the right constructor
	// To remove a link, use getCategorizedItems().remove()
	public Set<CategorizedItem> getCategorizedItems() {
		return categorizedItems;
	}

	// Many-to-many with additional columns on join table, intermediate component
	// class
	public Set<CategorizedItemComponent> getCategorizedItemComponents() {
		return categorizedItemComponents;
	}

	// Many-to-many with additional columns on join table, ternary hash map
	// representation
	public Map<Item, SimpleUser> getItemsAndUser() {
		return itemsAndUser;
	}

	public Date getCreated() {
		return new Date(created.getTime());
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final Category category = (Category) o;

		if (!created.equals(category.created))
			return false;
		if (!name.equals(category.name))
			return false;
		return !(parentCategory != null ? !parentCategory.equals(category.parentCategory)
				: category.parentCategory != null);

	}

	public int hashCode() {
		int result;
		result = name.hashCode();
		result = 29 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
		result = 29 * result + created.hashCode();
		return result;
	}

	public int compareTo(Category o) {
		return this.getName().compareTo(((Category) o).getName());		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=").append(id).append(", name=").append(name).append(", childCategories=")
				.append(childCategories).append(", parentCategory=").append(parentCategory).append(", items=")
				.append(items).append(", categorizedItems=").append(categorizedItems)
				.append(", categorizedItemComponents=").append(categorizedItemComponents).append(", itemsAndUser=")
				.append(itemsAndUser).append(", created=").append(created).append("]");
		return builder.toString();
	}
	

}
