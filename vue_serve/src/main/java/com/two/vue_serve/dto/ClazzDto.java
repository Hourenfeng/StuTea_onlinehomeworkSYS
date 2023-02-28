package com.two.vue_serve.dto;

import com.two.vue_serve.po.Clazz;

public class ClazzDto extends Clazz {
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
