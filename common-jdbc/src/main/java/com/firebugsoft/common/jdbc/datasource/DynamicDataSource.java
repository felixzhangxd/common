package com.firebugsoft.common.jdbc.datasource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private Logger logger = LoggerFactory.getLogger("sql");
	/** 主数据库 */
	private DataSource masterDataSource;
	/** 从数据库 */
	private DataSource slaveDataSource;

	// private AtomicInteger counter = new AtomicInteger();

	public DataSource getMasterDataSource() {
		return masterDataSource;
	}

	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	public DataSource getSlaveDataSource() {
		return slaveDataSource;
	}

	public void setSlaveDataSource(DataSource slaveDataSource) {
		this.slaveDataSource = slaveDataSource;
	}

	@Override
	public void setTargetDataSources(Map targetDataSources) {
		logger.info("DynamicDataSource.setTargetDataSources()");
		super.setTargetDataSources(targetDataSources);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		logger.info("DynamicDataSource.determineCurrentLookupKey()");
		return DataSourceHolder.getHolderKey();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		logger.info("DynamicDataSource.determineTargetDataSource()");
		if (DataSourceHolder.isSlave()) {
			return slaveDataSource;
		}
		if(DataSourceHolder.isMaster()) {
			return masterDataSource;	
		}
		return masterDataSource;
	}
}
