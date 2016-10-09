package com.firebugsoft.common.jdbc.sql;


import com.firebugsoft.common.bean.core.Page;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class SelectSqlBuilder {
	private List<String> columns;
	private List<String> tables;
	private List<String> wheres;
	private List<String> groups;
	private List<String> havings;
	private List<String> orders;
	private Page page;
	
	public void addColumn(String expression) {
        if(this.columns == null) {
            this.columns = new LinkedList<>();
        }
        columns.add(expression);
	}
	
	public void addTable(String table) {
        if(this.tables == null) {
            this.tables = new LinkedList<>();
        }
        tables.add(table);
	}
	
	public void addWhere(String expression, Object value, List<Object> values) {
		if(StringUtils.isEmpty(value)) {
    		return;
    	}
        if(this.wheres == null) {
            this.wheres = new LinkedList<>();
        }
        wheres.add(expression);
        values.add(value);
	}
	
    public void addGroup(String column) {
        if(this.groups == null) {
            this.groups = new LinkedList<>();
        }
        groups.add(column);
    }
    
    public void addHaving(String expression) {
        if(this.havings == null) {
            this.havings = new LinkedList<>();
        }
        havings.add(expression);
    }

    public void addOrder(String column, String sort) {
        if(this.orders == null) {
            this.orders = new LinkedList<>();
        }
        orders.add(column + " " + sort);
    }
    
    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder(256);
        sql.append("SELECT ");
        sql.append(this.columns == null ? "*" : StringUtils.collectionToDelimitedString(this.columns, ","));
        sql.append(" FROM ").append(StringUtils.collectionToDelimitedString(this.tables, ","));
        if(wheres != null) {
            sql.append(" WHERE ").append(StringUtils.collectionToDelimitedString(this.wheres, " AND "));
        }
        if(this.groups != null) {
            sql.append(" GROUP BY ").append(StringUtils.collectionToDelimitedString(this.groups, ","));
        }
        if(this.havings != null) {
            sql.append(" HAVING ").append(StringUtils.collectionToDelimitedString(this.havings, ","));
        }
        if(this.orders != null) {
            sql.append(" ORDER BY ").append(StringUtils.collectionToDelimitedString(this.orders, ","));
        }
        if(this.page != null) {
            sql.append(" LIMIT " + page.offset() + "," + page.limit());
        }
        return sql.toString();
    }
}
