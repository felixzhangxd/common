package com.firebugsoft.common.jdbc.datasource;

/**
 * 数据源持有者
 * @author felix
 */
public final class DataSourceHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    private DataSourceHolder() {};

    public static void setMasterDataSource() {
        holder.set("master");
    }

    public static void setSlaveDataSource() {
        holder.set("slave");
    }

    public static String getHolderKey() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
