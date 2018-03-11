package com.bac.orm.beans;

/**
 * This billing strategy uses a simple bank account.
 *
 * @author Christian Bauer
 */
public class BankAccount extends BillingDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 244813010730832193L;
	private String account;
	private String bankname;
	private String swift;

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public BankAccount() { super(); }

	/**
	 * Full constructor.
	 *
	 * @param owner
	 * @param user
	 * @param account
	 * @param bankname
	 * @param swift
	 */
	public BankAccount(String owner, SimpleUser user, String account, String bankname, String swift) {
		super(owner, user);
		this.account = account;
		this.bankname = bankname;
		this.swift = swift;
	}

	// ********************** Accessor Methods ********************** //

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getBankname() { return bankname; }
    public void setBankname(String bankname) { this.bankname = bankname; }

    public String getSwift() { return swift; }
	public void setSwift(String swift) { this.swift = swift; }

	// ********************** Common Methods ********************** //

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((bankname == null) ? 0 : bankname.hashCode());
		result = prime * result + ((swift == null) ? 0 : swift.hashCode());
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
		BankAccount other = (BankAccount) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (bankname == null) {
			if (other.bankname != null)
				return false;
		} else if (!bankname.equals(other.bankname))
			return false;
		if (swift == null) {
			if (other.swift != null)
				return false;
		} else if (!swift.equals(other.swift))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"bankAccount\": [\"account\":\"").append(account).append("\", \"bankname\":\"").append(bankname)
				.append("\", \"swift\":\"").append(swift).append("\"]");
		return builder.toString();
	}

	// ********************** Business Methods ********************** //

	public boolean isValid() {
		// TODO: Validate bank account syntax.
		return true;
	}

}
