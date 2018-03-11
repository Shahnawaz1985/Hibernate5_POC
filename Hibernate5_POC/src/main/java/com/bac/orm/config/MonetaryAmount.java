package com.bac.orm.config;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class MonetaryAmount implements Serializable, Comparable<MonetaryAmount> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092211362431210798L;

	private final BigDecimal value;
	private final Currency currency;

	public MonetaryAmount(BigDecimal value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public static MonetaryAmount fromString(String amount, String currencyCode) {
		return new MonetaryAmount(new BigDecimal(amount), Currency.getInstance(currencyCode));
	}

	public static MonetaryAmount convert(MonetaryAmount amount, Currency toConcurrency) {
		return new MonetaryAmount(amount.getValue(), toConcurrency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		MonetaryAmount other = (MonetaryAmount) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MonetaryAmount [value=" + value + ", currency=" + currency + "]";
	}

	@Override
	public int compareTo(MonetaryAmount o) {
		 return this.getValue().compareTo(((MonetaryAmount) o).getValue());		
	}

}