package com.br.professor_x_web.constent;

/**
 *
 * @author xin.cao@100credit.com
 */
public enum UserRole {

    COMMON(1, "common"),
    ADMIN(2, "admin"),
    SUPER_ADMIN(3, "super_admin"),
    COMMON_ADMIN(4, "common:admin"),
    COMMON_ADMIN_SUPER_ADMIN(5, "common:admin:super_admin");

    private int id;
    private String key;

    private UserRole(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public static UserRole fromId(int id) {
        for (UserRole userRole : values()) {
            if (userRole.getId() == id) {
                return userRole;
            }
        }
        return null;
    }
}
