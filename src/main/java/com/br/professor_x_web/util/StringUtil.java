package com.br.professor_x_web.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 字符串
 *
 * @author xin.cao@100credit.com
 */
public class StringUtil {

    public static String randomStr(int length) {
        return RandomStringUtils.random(length, "aAbBcCdDeEfFgGhHijJkKLmMnNoOpPqQrRsStTuUvVwWxXyYzZ123456789");
    }
}
