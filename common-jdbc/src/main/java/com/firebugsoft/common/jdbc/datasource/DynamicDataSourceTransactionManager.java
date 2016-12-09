package com.firebugsoft.common.jdbc.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = -5603994797161036080L;
	private Logger logger = LoggerFactory.getLogger("sql");

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		logger.info("DynamicDataSourceTransactionManager.doBegin()");
		if (definition.isReadOnly()) {
			DataSourceHolder.setSlave();
		} else {
			DataSourceHolder.setMaster();
		}
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		logger.info("DynamicDataSourceTransactionManager.doCleanupAfterCompletion()");
		super.doCleanupAfterCompletion(transaction);
		DataSourceHolder.clear();
	}
}
