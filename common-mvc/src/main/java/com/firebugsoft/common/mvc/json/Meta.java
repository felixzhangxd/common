package com.firebugsoft.common.mvc.json;

/**
 * json响应中的meta头
 * 
 * @author felix
 */
public class Meta {
    public static final Meta _200 = new Meta(200, "OK");
    public static final Meta _400 = new Meta(400, "Bad Request");
    public static final Meta _404 = new Meta(404, "Not Found");
    public static final Meta _500 = new Meta(500, "Internal Server Error");
    /** 状态码 */
    private int              code;
    /** 状态信息 */
    private String           msg;

    public Meta(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
