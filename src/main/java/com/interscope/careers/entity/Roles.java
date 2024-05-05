package com.interscope.careers.entity;

public enum Roles {
    RECRUITER(1), CANDIDATE(2);

    private Integer roleId;

    public Integer getRoleId() {
        return this.roleId;
    }

    private Roles(Integer roleId) {
        this.roleId = roleId;
    }
}
