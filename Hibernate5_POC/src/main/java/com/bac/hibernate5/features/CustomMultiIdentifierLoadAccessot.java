package com.bac.hibernate5.features;

import java.io.Serializable;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.LockOptions;
import org.hibernate.MultiIdentifierLoadAccess;

import com.bac.orm.beans.SimpleUser;

public class CustomMultiIdentifierLoadAccessot implements MultiIdentifierLoadAccess<SimpleUser> {

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> with(LockOptions lockOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> with(CacheMode cacheMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> withBatchSize(int batchSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> enableSessionCheck(boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> enableReturnOfDeletedEntities(boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiIdentifierLoadAccess<SimpleUser> enableOrderedReturn(boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends Serializable> List<SimpleUser> multiLoad(K... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends Serializable> List<SimpleUser> multiLoad(List<K> ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
