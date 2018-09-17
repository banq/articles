package com.doublexman.uum.domain;

public class UserRoleView {

    private String userId;

    private String userName;

    private String roleName;

    private String rolePermission;

    public UserRoleView() {
    }

    public UserRoleView(String userId, String userName, String roleName, String rolePermission) {
        this.userId = userId;
        this.userName = userName;
        this.roleName = roleName;
        this.rolePermission = rolePermission;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(String rolePermission) {
        this.rolePermission = rolePermission;
    }
}
