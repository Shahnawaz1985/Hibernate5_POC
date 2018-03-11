package com.bac.orm.config;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.CurrencyType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class MonetaryAmountUserType implements UserType, ParameterizedType {
	
	private Currency convertTo;

	@Override
	public int[] sqlTypes() {
		return new int[] {BigDecimalType.INSTANCE.sqlType(), CurrencyType.INSTANCE.sqlType()};//	
	}

	@Override
	public Class<MonetaryAmount> returnedClass() {
		return MonetaryAmount.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if(x == y)
			return true;
		if(x == null || y ==null)
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		BigDecimal valueInUSD = rs.getBigDecimal(names[0]);
		if(rs.wasNull())
			return null;
		Currency userCurrency = Currency.getInstance(rs.getString(names[1]));
		MonetaryAmount amount = new MonetaryAmount(valueInUSD, userCurrency);
		return amount;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public void setParameterValues(Properties parameters) {
		this.convertTo = Currency.getInstance(parameters.getProperty("convertTo"));
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		BigDecimal valueInUSD = rs.getBigDecimal(names[0]);
		if(rs.wasNull())
			return null;
		Currency userCurrency = Currency.getInstance(rs.getString(names[1]));
		MonetaryAmount amount = new MonetaryAmount(valueInUSD, userCurrency);
		return amount;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, BigDecimalType.INSTANCE.sqlType());
		}else {
			MonetaryAmount amount = (MonetaryAmount)value;
			MonetaryAmount dbAmount = MonetaryAmount.convert(amount, convertTo);
			st.setBigDecimal(index, dbAmount.getValue());
			st.setString(index+1, dbAmount.getCurrency().getCurrencyCode());
		}		
	}

	/*@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, Hibernate.BIG_DECIMAL.sqlType());
		}else {
			MonetaryAmount amount = (MonetaryAmount)value;
			MonetaryAmount dbAmount = MonetaryAmount.convert(amount, convertTo);
			st.setBigDecimal(index, dbAmount.getValue());
			st.setString(index+1, dbAmount.getCurrency().getCurrencyCode());
		}
		
	}*/

}
