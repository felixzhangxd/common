package com.firebugsoft.common.jdbc.sql;

/**
 * 组合模式:leaf
 * where condition
 */
public class Condition implements ICondition {
	private String condition;

	public Condition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return condition;
	}
}
