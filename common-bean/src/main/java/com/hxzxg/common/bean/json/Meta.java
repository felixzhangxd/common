package com.hxzxg.common.bean.json;

/**
 * json响应中的meta头
 * 
 * @author felix
 */
public class Meta {
    public static final Meta CODE_200 = new Meta(200, "OK");
    public static final Meta CODE_400 = new Meta(400, "Bad Request");
    public static final Meta CODE_401 = new Meta(401, "Unauthorized");
    public static final Meta CODE_404 = new Meta(404, "Not Found");
    public static final Meta CODE_500 = new Meta(500, "Internal Server Error");
    /** 状态码 */
    private int              code;
    /** 状态信息 */
    private String           msg;

    private Meta(int code, String msg) {
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
