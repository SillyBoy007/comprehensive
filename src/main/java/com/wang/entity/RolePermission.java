package com.wang.entity;

public class RolePermission {
    private String id;

    private String roleid;

    private String permissionid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid == null ? null : permissionid.trim();
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id='" + id + '\'' +
                ", roleid='" + roleid + '\'' +
                ", permissionid='" + permissionid + '\'' +
                '}';
    }
}