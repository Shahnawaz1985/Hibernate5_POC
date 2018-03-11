package com.bac.orm.beans;

/**
 * This billing strategy can handle various credit cards.
 * <p>
 * The type of credit card is handled with a typesafe
 * enumeration, <tt>CreditCardType</tt>.
 *
 * @see CreditCardType
 * @author Christian Bauer
 */
public class CreditCard extends BillingDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099299189682387110L;
	private CreditCardType type;
	private String number;
	private String creditCardType;
	private String expMonth;
	private String expYear;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public CreditCard() { super(); }

	/**
	 * Full constructor.
	 *
	 * @param owner
	 * @param user
	 * @param type
	 * @param expMonth
	 * @param expYear
	 */
	public CreditCard(String owner, SimpleUser user, String number, CreditCardType type, String creditCardType,
					  String expMonth, String expYear) {
		super(owner, user);
		this.type = type;
		this.creditCardType = creditCardType;
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	// ********************** Accessor Methods ********************** //

	public CreditCardType getType() {
		return type;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public String getNumber() {
		return number;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	// ********************** Common Methods ********************** //

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditCardType == null) ? 0 : creditCardType.hashCode());
		result = prime * result + ((expMonth == null) ? 0 : expMonth.hashCode());
		result = prime * result + ((expYear == null) ? 0 : expYear.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (creditCardType == null) {
			if (other.creditCardType != null)
				return false;
		} else if (!creditCardType.equals(other.creditCardType))
			return false;
		if (expMonth == null) {
			if (other.expMonth != null)
				return false;
		} else if (!expMonth.equals(other.expMonth))
			return false;
		if (expYear == null) {
			if (other.expYear != null)
				return false;
		} else if (!expYear.equals(other.expYear))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"creditCard\": [\"type\":\"").append(type).append("\", \"number\":\"").append(number).append("\", \"creditCardType\":\"")
				.append(creditCardType).append("\", \"expMonth\":\"").append(expMonth).append("\", \"expYear\":\"").append(expYear)
				.append("\"]");
		return builder.toString();
	}

	// ********************** Business Methods ********************** //

	public boolean isValid() {
		// Use the type to validate the CreditCard details.
		return getType().isValid(this);
	}

}
