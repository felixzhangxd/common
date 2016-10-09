package com.firebugsoft.common.mvc.json;

import com.firebugsoft.common.bean.core.Page;

/**
 * json响应中的body
 * 
 * @author felix
 */
public class Body {
    /** OK */
    public static Body _200 = new Body(Meta._200);
    /** Bad Request */
    public static Body _400 = new Body(Meta._400);
    /** Not Found */
    public static Body _404 = new Body(Meta._404);
    /** Internal Server Error */
    public static Body _500 = new Body(Meta._500);

    public static Body newInstance() {
        return Body._200;
    }

    public static Body newInstance(Meta meta) {
        return new Body(meta);
    }

    public static Body newInstance(Object data) {
        return new Body(Meta._200, data);
    }

    public static Body newInstance(Meta meta, Object data) {
        return new Body(meta, data);
    }

    public static Body newInstance(Meta meta, Object data, Page page) {
        return new Body(meta, data, page);
    }

    private Meta   meta;
    private Object data;
    private Page   page;

    private Body(Meta meta) {
        this(meta, null);
    }

    private Body(Meta meta, Object data) {
        this(meta, data, null);
    }

    private Body(Meta meta, Object data, Page page) {
        this.meta = meta;
        this.data = data;
        this.page = page;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public Page getPage() {
        return page;
    }
}
