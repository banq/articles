package com.doublexman.microservice.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "example_role")
public class Role extends AbstractEntity {

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "permission")
    private String rolePermission;

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
