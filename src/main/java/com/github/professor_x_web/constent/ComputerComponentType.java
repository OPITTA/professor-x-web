/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.constent;

/**
 *
 * @author 510655387@qq.com
 */
public enum ComputerComponentType {

    CPU(1, "CPU"),
    MEMORY(2, "内存"),
    DIST(3, "磁盘"),
    SOLID_DIST(4, "固态硬盘"),
    NERWORK_CARD(5, "网卡");
    private int id;
    private String name;

    private ComputerComponentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ComputerComponentType fromInt(int id) {
        for (ComputerComponentType c : values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
