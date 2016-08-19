package com.hxzxg.common.bean.json;

import com.hxzxg.common.bean.core.Page;

/**
 * json响应中的body
 * 
 * @author felix
 */
public class Body {
    /** 请求成功 */
    public static Body BODY_200 = new Body(Meta.CODE_200);
    /** 错误请求(参数错误) */
    public static Body BODY_400 = new Body(Meta.CODE_400);
    /** 未授权 */
    public static Body BODY_401 = new Body(Meta.CODE_401);
    /** 请求不存在 */
    public static Body BODY_404 = new Body(Meta.CODE_404);
    /** 服务器内部错误 */
    public static Body BODY_500 = new Body(Meta.CODE_500);

    public static Body newInstance() {
        return Body.BODY_200;
    }

    public static Body newInstance(Object data) {
        return new Body(Meta.CODE_200, data);
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
