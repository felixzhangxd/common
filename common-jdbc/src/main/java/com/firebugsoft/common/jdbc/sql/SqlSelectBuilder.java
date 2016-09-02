package com.firebugsoft.common.jdbc.sql;


import com.hxzxg.common.bean.core.Page;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SqlSelectBuilder {
	private List<String> columns;
	private String table;
	private ConditionGroup where;
	private List<String> groups;
	private List<String> orders;
	private Page page;

    public void setColumn(String column) {
        if(this.columns == null) {
            this.columns = new ArrayList();
        }
        this.columns.add(column);
    }
    public void setColumn(String column, String as) {
        setColumn(column + " AS " +  as);
    }
    public void setColumnCount() {
        setColumnCount("*");
    }
    public void setColumnCount(String column) {
        setColumn("COUNT(" + column + ")");
    }
    public void setColumnCount(String column, String as) {
        setColumn("COUNT(" + column + ")", as);
    }
    public void setColumnCountDistinct(String column) {
        setColumn("COUNT(DISTINCT " + column + ")");
    }
    public void setColumnCountDistinct(String column, String as) {
        setColumn("COUNT(DISTINCT " + column + ")", as);
    }
    public void setColumnSum(String column, String as) {
        setColumn("SUM(" + column + ")", as);
    }
    public void setColumnAvg(String column, String as) {
        setColumn("AVG(" + column + ")", as);
    }
    public void setColumnMax(String column, String as) {
        setColumn("MAX(" + column + ")", as);
    }
    public void setColumnMin(String column, String as) {
        setColumn("MIN(" + column + ")", as);
    }
    public void setColumnDistinct(String column) {
        setColumn("DISTINCT " + column);
    }

    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }

    public void setWhere(ConditionGroup where) {
        this.where =  where;
    }

    public void addGroup(String column) {
        if(this.groups == null) {
            this.groups = new LinkedList<>();
        }
        groups.add(column);
    }

    public void addOrderByAsc(String column) {
        if(this.orders == null) {
            this.orders = new LinkedList<>();
        }
        orders.add(column + " ASC");
    }
    public void addOrderByDesc(String column) {
        if(this.orders == null) {
            this.orders = new LinkedList<>();
        }
        orders.add(column + " DESC");
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        if(this.columns == null || this.columns.isEmpty()) {
            sql.append("*");
        }else {
            sql.append(StringUtils.collectionToDelimitedString(this.columns, ","));
        }
        sql.append(" FROM ").append(this.table);
        if(where != null) {
            sql.append(" WHERE ").append(where);
        }
        if(this.groups != null) {
            sql.append(" GROUP BY ").append(StringUtils.collectionToDelimitedString(this.groups, ","));
        }
        if(this.orders != null) {
            sql.append(" ORDER BY ").append(StringUtils.collectionToDelimitedString(this.orders, ","));
        }
        if(this.page != null) {
            sql.append(" LIMIT " + page.offset() + "," + page.limit());
        }
        return sql.toString();
    }

    public static void main(String[] args) {
        ConditionGroup where = new ConditionGroup(ConditionGroup.Operator.AND);
        List<Object> values = new LinkedList<>();
        where.addEqual("id", 1, values);
        where.addEqual("name", 2, values);
        //
        SqlSelectBuilder sql = new SqlSelectBuilder();
        sql.setTable("t_user");
        sql.setWhere(where);
//        sql.addGroup("group_by1");
//        sql.addGroup("group_by2");
//        sql.addOrderByAsc("id");
//        sql.addOrderByDesc("name");
//        sql.setPage(new Page());

        System.out.println(sql);
        System.out.println(values);
    }
}
