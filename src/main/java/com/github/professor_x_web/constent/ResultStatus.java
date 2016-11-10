package com.github.professor_x_web.constent;

/**
 *
 * @author 510655387@qq.com
 */
public enum ResultStatus {

    YES(1, "成功"),
    NO(2, "失败"),;

    private int no;
    private String name;

    private ResultStatus(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public static boolean isYES(int no) {
        for (ResultStatus rs : values()) {
            if (rs.getNo() == no) {
                return rs == YES;
            }
        }
        return false;
    }
}
