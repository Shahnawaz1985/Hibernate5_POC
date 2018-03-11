package com.bac.orm.beans;

import java.io.Serializable;
import java.util.Date;

import com.bac.orm.config.MonetaryAmount;


public class Bid implements Serializable, Comparable<Bid> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 270905361965512499L;

	private Long id = null;
	private MonetaryAmount amount;
	private Item item;
	private SimpleUser bidder;
	private Date created = new Date();
	private boolean successful = false;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public Bid() {
	}

	/**
	 * Full constructor
	 *
	 * @param amount
	 * @param item
	 * @param bidder
	 */

	public Bid(MonetaryAmount amount, Item item, SimpleUser bidder) {
		this.amount = amount;
		this.item = item;
		this.bidder = bidder;
	}

	// ********************** Accessor Methods ********************** //

	public Long getId() {
		return id;
	}

	public MonetaryAmount getAmount() {
		return amount;
	}

	public Item getItem() {
		return item;
	}

	public SimpleUser getBidder() {
		return bidder;
	}

	public Date getCreated() {
		return new Date(created.getTime())	;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	// ********************** Common Methods ********************** //

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
		Bid other = (Bid) obj;
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
		builder.append("Bid [id=").append(id).append(", amount=").append(amount).append(", item=").append(item)
				.append(", bidder=").append(bidder).append(", created=").append(created).append(", successful=")
				.append(successful).append("]");
		return builder.toString();
	}

	public int compareTo(Bid o) {
		return Long.compare(this.getCreated().getTime(), o.getCreated().getTime());
	}

}
