package com.github.professor_x_web.constent;

/**
 *
 * @author 510655387@qq.com
 */
public enum UserRole {

    COMMON(1, "common"),
    ADMIN(2, "admin"),
    COMMON_ADMIN(3, "common:admin");

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
