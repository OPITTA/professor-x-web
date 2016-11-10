/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.constent;

/**
 *
 * @author 510655387@qq.com
 */
public enum ReportStatus {

    NEW(1, "新建"),
    DOING(2, "进行中"),
    DONE(3, "完成"),
    SHARED(4, "分享");
    private int id;
    private String name;

    private ReportStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ReportStatus fromInt(int id) {
        for (ReportStatus r : values()) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}
