package com.firebugsoft.common.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<T extends Serializable> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public abstract JdbcTemplate getJdbcTemplate();

    public int save(T t) throws IllegalAccessException {
        Table table = t.getClass().getAnnotation(Table.class);
        List<String> columns = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        List<String> args = new LinkedList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column == null) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(t);
            if (value != null) {
                columns.add("" + column.name() + "");
                args.add("?");
                values.add(value instanceof Enum ? value.toString() : value);
            }
        }
        String column = StringUtils.collectionToDelimitedString(columns, ",");
        String arg = StringUtils.collectionToDelimitedString(args, ",");
        String sql = String.format("INSERT INTO %s.%s (%s) VALUES (%s)", table.catalog(), table.name(), column, arg);
        logger.info("{}\r\n{}", sql, values);
        return this.getJdbcTemplate().update(sql, values.toArray());
    }

    public void removeById(T po) throws IllegalAccessException {
        Table table = po.getClass().getAnnotation(Table.class);
        List<String> wheres = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Field field : po.getClass().getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (id == null) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            wheres.add("" + column.name() + "=?");
            field.setAccessible(true);
            values.add(field.get(po));
        }
        String where = StringUtils.collectionToDelimitedString(wheres, " AND ");
        String sql = String.format("DELETE FROM %s.%s WHERE %s", table.catalog(), table.name(), where);
        logger.info("{}\r\n{}", sql, values);
        this.getJdbcTemplate().update(sql, values.toArray());
    }

    public int modifyById(T po) throws IllegalAccessException {
        Table table = po.getClass().getAnnotation(Table.class);
        List<String> columns = new LinkedList<>();
        List<Object> columnValues = new LinkedList<>();
        List<String> wheres = new LinkedList<>();
        List<Object> whereValues = new LinkedList<>();
        for (Field field : po.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if(column == null) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(po);
            if (field.getAnnotation(Id.class) != null) {
                wheres.add("" + column.name() + "=?");
                whereValues.add(value instanceof Enum ? value.toString() : value);
                continue;
            }
            if(value != null){
                columns.add("" + column.name() + "=?");
                columnValues.add(value instanceof Enum ? value.toString() : value);
            }
        }
        String column = StringUtils.collectionToDelimitedString(columns, ",");
        String where = StringUtils.collectionToDelimitedString(wheres, " AND ");
        String sql = String.format("UPDATE %s.%s SET %s WHERE %s", table.catalog(), table.name(), column, where);
        columnValues.addAll(whereValues);
        logger.info("{}\r\n{}", sql, columnValues);
        return this.getJdbcTemplate().update(sql, columnValues.toArray());
    }

}