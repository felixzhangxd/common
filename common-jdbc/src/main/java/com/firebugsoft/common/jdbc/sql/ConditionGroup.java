package com.firebugsoft.common.jdbc.sql;

import com.hxzxg.common.bean.utils.CharacterUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式:Composite
 * where condition
 */
public class ConditionGroup implements ICondition {
	enum Operator {
		AND, OR;
	}
	private Operator operator;
	private List<ICondition> sql = new ArrayList<>();

	public ConditionGroup() {
		this(Operator.AND);
	}

	public ConditionGroup(Operator operator) {
		this.operator = operator;
	}

	public ConditionGroup addIsNull(String column){
		sql.add(new Condition(column + " IS NULL"));
		return this;
	}
	public ConditionGroup addIsNotNull(String column){
		sql.add(new Condition(column + " IS NOT NULL"));
		return this;
	}
	public ConditionGroup addEqual(String column, Object value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + "=?"));
			values.add(value);
		}
		return this;
	}
	public ConditionGroup addNotEqual(String column, Object value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + "<>?"));
			values.add(value);
		}
		return this;
	}
	public ConditionGroup addGreaterEqual(String column, Object value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + ">=?"));
			values.add(value);
		}
		return this;
	}
	public ConditionGroup addLessEqual(String column, Object value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + "<=?"));
			values.add(value);
		}
		return this;
	}
	public ConditionGroup addIn(String column, List<Object> value, List<Object> values){
		if(value != null && value.size() > 0) {
			sql.add(new Condition(column + " IN (" + CharacterUtils.repeat("?", ",", value.size()) + ")"));
			values.addAll(value);
		}
		return this;
	}
	public ConditionGroup addNotIn(String column, List<Object> value, List<Object> values){
		if(value != null && value.size() > 0) {
			sql.add(new Condition(column + " NOT IN (" + CharacterUtils.repeat("?", ",", value.size()) + ")"));
			values.addAll(value);
		}
		return this;
	}
	public ConditionGroup addLike(String column, Object value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + " LIKE %?%"));
			values.add(value);
		}
		return this;
	}
	public ConditionGroup addNotLike(String column, String value, List<Object> values){
		if(value != null) {
			sql.add(new Condition(column + " NOT LIKE %?%"));
			values.add(value);
		}
		return this;
	}

	public ConditionGroup addCondition(ICondition condition) {
		sql.add(condition);
		return this;
	}

	public String toString() {
		if(operator == Operator.AND) {
			return StringUtils.collectionToDelimitedString(sql, " " + operator + " ");
		}
		if(operator == Operator.OR) {

		}
		return "(" + StringUtils.collectionToDelimitedString(sql, " " + operator + " ") + ")";
	}
}
