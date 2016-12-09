package com.firebugsoft.common.jdbc.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataSourceHolder {
	private static final Logger logger = LoggerFactory.getLogger("sql");
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	private DataSourceHolder() {};
	
	public static boolean isMaster() {
		return "master".equals(holder.get());
	}

	public static void setMaster() {
		logger.info("DataSourceHolder.setMaster()");
		holder.set("master");
	}

	public static void setSlave() {
		logger.info("DataSourceHolder.setSlave()");
		holder.set("slave");
	}

	public static boolean isSlave() {
		return "slave".equals(holder.get());
	}

	public static String getHolderKey() {
		return holder.get();
	}

	public static void clear() {
		holder.remove();
	}
}
