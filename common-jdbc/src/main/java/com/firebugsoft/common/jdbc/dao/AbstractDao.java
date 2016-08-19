package com.firebugsoft.common.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<T extends Serializable> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract JdbcTemplate getJdbcTemplate();

    public void save(T t) throws IllegalAccessException {
        Entity entity = t.getClass().getAnnotation(Entity.class);
        if (entity == null) {
            return;
        }
        List<String> columns = new LinkedList<>();
        List<String> args = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column == null) {
                continue;
            }
            field.setAccessible(true);
            if (field.get(t) != null) {
                columns.add(column.name());
                args.add("?");
                values.add(field.get(t));
            }
        }
        String tableName = entity.name();
        String column = StringUtils.collectionToDelimitedString(columns, ",");
        String arg = StringUtils.collectionToDelimitedString(args, ",");
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, column, arg);
        logger.info("{}\r\n{}", sql, values);
        this.getJdbcTemplate().update(sql, values.toArray());
    }

    public void removeByPk(T t) throws IllegalAccessException {
        Entity entity = t.getClass().getAnnotation(Entity.class);
        if (entity == null) {
            return;
        }
        List<String> wheres = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            Id id = field.getAnnotation(Id.class);
            if (id == null) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            wheres.add(column.name() + "=?");
            field.setAccessible(true);
            values.add(field.get(t));
        }
        String tableName = entity.name();
        String where = StringUtils.collectionToDelimitedString(wheres, " AND ");
        String sql = String.format("DELETE FROM %s WHERE %s", tableName, where);
        logger.info("{}\r\n{}", sql, values);
        this.getJdbcTemplate().update(sql, values.toArray());
    }

    public void modifyByPk(T t) throws IllegalAccessException {
        Entity entity = t.getClass().getAnnotation(Entity.class);
        if (entity == null) {
            return;
        }
        List<String> columns = new LinkedList<>();
        List<String> wheres = new LinkedList<>();
        List<Object> values = new LinkedList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if(column == null) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(t);
            if(value == null) {
                continue;
            }
            if (field.getAnnotation(Id.class) == null) {
                columns.add(column.name() + "=?");
            }else {
                wheres.add(column.name() + "=?");
            }
            values.add(value);
        }
        String tableName = entity.name();
        String column = StringUtils.collectionToDelimitedString(columns, ",");
        String where = StringUtils.collectionToDelimitedString(wheres, " AND ");
        String sql = String.format("UPDATE %s SET %s WHERE %s", tableName, column, where);
        logger.info("{}\r\n{}", sql, values);
        this.getJdbcTemplate().update(sql, values.toArray());
    }
}