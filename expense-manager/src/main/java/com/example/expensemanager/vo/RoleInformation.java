package com.example.expensemanager.vo;

public class RoleInformation {

    private RoleType roleType;
    private String roleId;

    @SuppressWarnings("unused")
    public RoleInformation() {
    }

    public RoleInformation(RoleType roleType, String roleId) {
        this.roleType = roleType;
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getRoleId() {
        return roleId;
    }
}
