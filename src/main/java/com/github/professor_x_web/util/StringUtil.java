package com.github.professor_x_web.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 字符串
 *
 * @author 510655387@qq.com
 */
public class StringUtil {

    public static String randomStr(int length) {
        return RandomStringUtils.random(length, "aAbBcCdDeEfFgGhHijJkKLmMnNoOpPqQrRsStTuUvVwWxXyYzZ123456789");
    }
}
