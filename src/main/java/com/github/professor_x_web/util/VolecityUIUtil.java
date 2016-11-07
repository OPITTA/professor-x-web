package com.github.professor_x_web.util;

/**
 *
 * @author xin.cao@100credit.com
 */
public class VolecityUIUtil {

    private static VolecityUIUtil volecityUIUtil;

    private VolecityUIUtil() {
    }

    public static VolecityUIUtil getInstance() {
        if (volecityUIUtil == null) {
            VolecityUIUtil.volecityUIUtil = new VolecityUIUtil();
        }
        return VolecityUIUtil.volecityUIUtil;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int add(int x, int y) {
        return x + y;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int sub(int x, int y) {
        return x - y;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int mul(int x, int y) {
        return x * y;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int div(int x, int y) {
        if (y != 0) {
            return x / y;
        }
        return 0;
    }

    /**
     *
     * @param cellPhone
     * @return
     */
    public static String cellPhoneMask(String cellPhone) {
        if (ValidateUtil.isEmptyString(cellPhone) || cellPhone.length() < 7) {
            return cellPhone;
        }
        String before = cellPhone.substring(0, 3);
        String after = cellPhone.substring(cellPhone.length() - 4, cellPhone.length());
        return before + "***" + after;
    }

}
