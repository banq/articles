package com.doublexman.uum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "example_user_role")
public class UserRole extends AbstractEntity {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_uid")
    private String roleUid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleUid() {
        return roleUid;
    }

    public void setRoleUid(String roleUid) {
        this.roleUid = roleUid;
    }
}
