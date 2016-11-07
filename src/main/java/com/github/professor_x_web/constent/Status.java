package com.github.professor_x_web.constent;

/**
 *
 * @author xin.cao@100credit.com
 */
public enum Status {

    NOT_DONE(1),
    DOING(2),
    SUCCESS(3),
    FAILURE(4),
    ALREADY_EXIST(5)
    ;

    private int no;

    private Status(int no) {
        this.no = no;
    }

    public int getNo() {
        return this.no;
    }
}
