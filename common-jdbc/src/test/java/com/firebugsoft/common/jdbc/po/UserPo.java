package com.firebugsoft.common.jdbc.po;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class UserPo implements Serializable {
    private static final long serialVersionUID = -7236740888170832018L;
    private Integer           id;
    private String            name;

    public UserPo() {}

    public UserPo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
