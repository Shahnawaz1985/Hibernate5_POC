package com.bac.orm.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bac.exception.BusinessException;
import com.bac.exception.PermissionException;
import com.bac.orm.config.MonetaryAmount;

public class Item implements Comparable<Item>, Serializable, Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7494010258801818313L;

	private Long id = null;
	private String name;
	private SimpleUser seller;
	private SimpleUser buyer;
	private String description;

	private MonetaryAmount initialPrice;
	private MonetaryAmount reservePrice;
	private Timestamp startDate = new java.sql.Timestamp(new java.util.Date().getTime());
	private Timestamp endDate;

	private ItemState state;
	private SimpleUser approvedBy;
	private LocalDateTime approvalDatetime;

	private List<Bid> bids = new ArrayList<Bid>();
	private Bid successfulBid;
	private Map<Long, Bid> bidsByIdentifier = new HashMap<Long, Bid>(); // Not very useful

	private Set<Category> categories = new HashSet<Category>();
	private Set<CategorizedItem> categorizedItems = new HashSet<CategorizedItem>();

	private Collection<String> images = new ArrayList<String>();

	private Timestamp created = new java.sql.Timestamp(new java.util.Date().getTime());

	/**
	 * No-arg constructor for JavaBean tools.
	 */
	public Item() {
	}

	/**
	 * Full constructor
	 */
	public Item(String name, SimpleUser seller, SimpleUser buyer, String description, MonetaryAmount initialPrice,
			MonetaryAmount reservePrice, Timestamp endDate, ItemState state, SimpleUser approvedBy,
			LocalDateTime approvalDatetime, List<Bid> bids, Bid successfulBid, Map<Long, Bid> bidsByIdentifier,
			Set<Category> categories, Set<CategorizedItem> categorizedItems, Collection<String> images) {
		this.name = name;
		this.seller = seller;
		this.buyer = buyer;
		this.description = description;
		this.initialPrice = initialPrice;
		this.reservePrice = reservePrice;
		this.endDate = endDate;
		this.state = state;
		this.approvedBy = approvedBy;
		this.approvalDatetime = LocalDateTime.now();
		this.bids = bids;
		this.successfulBid = successfulBid;
		this.bidsByIdentifier = bidsByIdentifier;
		this.categories = categories;
		this.categorizedItems = categorizedItems;
		this.images = images;

		// Referential integrity
		seller.getItemsForSale().add(this);
	}

	/**
	 * Simple constructors
	 */
	public Item(String name, String description, SimpleUser seller, MonetaryAmount initialPrice,
			MonetaryAmount reservePrice, Timestamp endDate2) {
		this.name = name;
		this.seller = seller;
		this.description = description;
		this.initialPrice = initialPrice;
		this.reservePrice = reservePrice;
		this.endDate = endDate2;
		this.state = ItemState.DRAFT;

		// Referential integrity
		seller.getItemsForSale().add(this);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public SimpleUser getSeller() {
		return seller;
	}

	public SimpleUser getBuyer() {
		return buyer;
	}

	public void setBuyer(SimpleUser buyer) {
		this.buyer = buyer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MonetaryAmount getInitialPrice() {
		return initialPrice;
	}

	public MonetaryAmount getReservePrice() {
		return reservePrice;
	}

	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

	public ItemState getState() {
		return state;
	}

	public SimpleUser getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(SimpleUser approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovalDatetime() {
		return LocalDateTime.now();
	}

	public void setApprovalDatetime(LocalDateTime approvalDatetime) {
		this.approvalDatetime = approvalDatetime;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void addBid(Bid bid) {
		if (bid == null)
			throw new IllegalArgumentException("Can't add a null Bid.");
		this.getBids().add(bid);
		// Don't have to set the "other" side, a Bid can only be instantiated with a
		// given item
	}

	public Bid getSuccessfulBid() {
		return successfulBid;
	}

	public void setSuccessfulBid(Bid successfulBid) {
		// Has to preserve the integrity by using a procedure and loop, bad Java...
		if (successfulBid != null) {
			for (Bid bid : getBids())
				bid.setSuccessful(false);
			successfulBid.setSuccessful(true);
			this.successfulBid = successfulBid;
		}
	}

	public Map<Long, Bid> getBidsByIdentifier() {
		return bidsByIdentifier;
	}

	public void setBidsByIdentifier(Map<Long, Bid> bidsByIdentifier) {
		this.bidsByIdentifier = bidsByIdentifier;
	}

	// Read-only, modify through Category#addItem() and Category@removeItem();
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}

	// Read-only, to create a link, instantiate a CategorizedItem with the right
	// constructor
	// To remove a link, use Category.getCategorizedItems().remove()
	public Set<CategorizedItem> getCategorizedItems() {
		return categorizedItems;
	}

	public Collection<String> getImages() {
		return images;
	}

	public Timestamp getCreated() {
		return new java.sql.Timestamp(created.getTime());
	}

	@Override
	public int compareTo(Item o) {
		// Don't compare Date objects! Use the time in milliseconds!
		// return
		// Long.valueOf(this.getCreated().getTime()).compareTo(Long.valueOf(o.getCreated().getTime()));
		return Long.compare(this.getCreated().getTime(), o.getCreated().getTime());

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
		Item other = (Item) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		return true;
	}

	/**
	 * Places a bid while checking business constraints.
	 *
	 * This method may throw a BusinessException if one of the requirements for the
	 * bid placement wasn't met, e.g. if the auction already ended.
	 *
	 * @param bidder
	 * @param bidAmount
	 * @param currentMaxBid
	 *            the most valuable bid for this item
	 * @param currentMinBid
	 *            the least valuable bid for this item
	 * @return
	 * @throws BusinessException
	 */
	public Bid placeBid(SimpleUser bidder, MonetaryAmount bidAmount, Bid currentMaxBid, Bid currentMinBid)
			throws BusinessException {

		// Check initial price
		if (initialPrice.compareTo(bidAmount) > 0) {
			throw new BusinessException("Bid lower than initial price");
		}

		// Check reserve price
		if (reservePrice.compareTo(bidAmount) > 0) {
			throw new BusinessException("Bid lower than reserve price");
		}

		// Check highest bid (can also be a different Strategy (pattern))
		if (currentMaxBid != null && currentMaxBid.getAmount().compareTo(bidAmount) > 0) {
			throw new BusinessException("Bid too low");
		}

		// Auction is active
		if (!this.getState().equals(ItemState.ACTIVE))
			throw new BusinessException("Auction is not active yet");

		// Auction still valid
		if (this.getEndDate().before(new Date(new java.util.Date().getTime())))
			throw new BusinessException("Can't place new bid, auction already ended");

		// Create new Bid
		Bid newBid = new Bid(bidAmount, this, bidder);

		// Place bid for this Item
		this.addBid(newBid);

		return newBid;
	}

	/**
	 * Anyone can set this item into PENDING state for approval.
	 */
	public void setPendingForApproval() {
		state = ItemState.PENDING;
	}

	/**
	 * Approve this item for auction and set its state to active.
	 *
	 * @param byUser
	 * @throws BusinessException
	 */
	public void approve(SimpleUser byUser) throws BusinessException {

		if (!byUser.getAdmin().equals("Y"))
			throw new PermissionException("Not an administrator");

		if (!state.equals(ItemState.PENDING))
			throw new IllegalStateException("Item still in draft");

		state = ItemState.ACTIVE;
		approvedBy = byUser;
		approvalDatetime = LocalDateTime.now();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [id=").append(id).append(", name=").append(name).append(", description=").append(description).append(", initialPrice=")
				.append(initialPrice).append(", reservePrice=").append(reservePrice).append(", startDate=")
				.append(startDate).append(", endDate=").append(endDate).append(", state=").append(state)
				.append(", approvalDatetime=").append(approvalDatetime)
				.append(", bids=").append(bids).append(", successfulBid=").append(successfulBid)
				.append(", bidsByIdentifier=").append(bidsByIdentifier).append(", categories=").append(categories)
				.append(", categorizedItems=").append(categorizedItems).append(", images=").append(images)
				.append(", created=").append(created).append("]");
		return builder.toString();
	}

}
