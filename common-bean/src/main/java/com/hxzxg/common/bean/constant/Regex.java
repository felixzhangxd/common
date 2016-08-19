package com.hxzxg.common.bean.constant;

/**
 * @author felix
 */
public final class Regex {
    /** email正则 */
    public static final String EMAIL   = "^[a-zA-Z0-9_\\.\\-]+@(([a-zA-Z0-9_\\-]+)\\.)+[a-zA-Z0-9]+$";
    /** 手机号码正则 */
    public static final String MOBILE  = "^1[0-9]{10}$";
    /** 邮政编码正则 */
    public static final String ZIPCODE = "^[0-9]{6}$";

    private Regex() {}

}
