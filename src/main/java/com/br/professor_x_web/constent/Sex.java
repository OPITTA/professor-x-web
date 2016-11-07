package com.br.professor_x_web.constent;

/**
 * 性别
 *
 * @author xin.cao@100credit.com
 */
public enum Sex {

    MALE(1, "男性"), // 奇数
    FEMALE(0, "女性"); // 偶数
    private int no;
    private String name;

    private Sex(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return this.no;
    }

    public String getName() {
        return this.name;
    }

    public static Sex fromNo(int no) {
        for (Sex sex : values()) {
            if (sex.getNo() == no) {
                return sex;
            }
        }
        return null;
    }
}
