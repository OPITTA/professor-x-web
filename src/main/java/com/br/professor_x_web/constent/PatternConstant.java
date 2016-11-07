package com.br.professor_x_web.constent;

/**
 * 正则表达式
 *
 * @author xin.cao@100credit.com
 */
public class PatternConstant {

    public static final String MAIL = "^(?:\\w[-.\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)$";
    public static final String IP = "^\\d+\\.\\d+\\.\\d+\\.\\d+$";
    public static final String COMMON = "^\\w+$";
    public static final String ID_CARD_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";

}
