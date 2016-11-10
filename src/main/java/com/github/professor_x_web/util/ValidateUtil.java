package com.github.professor_x_web.util;

import com.github.professor_x_web.constent.PatternConstant;
import java.util.regex.Pattern;

/**
 * 对字符串进行校验
 *
 * @author 510655387@qq.com
 */
public class ValidateUtil {

    private static final Pattern MAIL_PATTERN = Pattern.compile(PatternConstant.MAIL);
    private static final Pattern IP_PATTERN = Pattern.compile(PatternConstant.IP);
    private static final Pattern COMMON_PATTERN = Pattern.compile(PatternConstant.COMMON);
    private static final Pattern ID_CARD_15_PATTERN = Pattern.compile(PatternConstant.ID_CARD_15);
    private static final Pattern ID_CARD_18_PATTERN = Pattern.compile(PatternConstant.ID_CARD_18);

    /**
     * 邮箱
     *
     * @param mail
     * @return
     */
    public static boolean isMail(String mail) {
        if (isEmptyString(mail)) {
            return false;
        }
        return MAIL_PATTERN.matcher(mail).matches();
    }

    /**
     * 点分十进制IP
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        if (isEmptyString(ip)) {
            return false;
        }
        return IP_PATTERN.matcher(ip).matches();
    }

    /**
     * 一般字符串校验（字母， 数字，下划线构成）
     *
     * @param common
     * @return
     */
    public static boolean isCommon(String common) {
        if (isEmptyString(common)) {
            return false;
        }
        return COMMON_PATTERN.matcher(common).matches();
    }

    /**
     * 身份证
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (isEmptyString(idCard)) {
            return false;
        }
        int len = idCard.length();
        if (len == 15) {
            return ID_CARD_15_PATTERN.matcher(idCard).matches();
        } else if (len == 18) {
            return ID_CARD_18_PATTERN.matcher(idCard).matches();
        } else {
            return false;
        }
    }

    /**
     * 通过脱敏字符串对输入身份证进行校验
     *
     * @param idCard 输入身份证
     * @param idCardMask 身份证的脱敏字符串
     * @param first 前几位匹配
     * @param last 后几位匹配
     * @return
     */
    public static boolean isIdCard(String idCard, String idCardMask, int first, int last) {
        if (!isIdCard(idCard)) {
            return false;
        }
        int maxLen = first > last ? first : last;
        if (ValidateUtil.isNotEmptyString(idCardMask) && ValidateUtil.isNotEmptyString(idCard) && idCardMask.length() > maxLen) {
            String beforeMask = idCardMask.substring(0, first);
            String afterMask = idCardMask.substring(idCardMask.length() - last, idCardMask.length());
            String before = idCard.substring(0, first);
            String after = idCard.substring(idCard.length() - last, idCard.length());
            if (before.equals(beforeMask) && after.contains(afterMask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmptyString(String str) {
        return !(str == null || "".equals(str));
    }

    /**
     * 空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str);
    }

    public static void main(String... args) {
        System.out.println(isMail("510655387@qq.com"));
        System.out.println(isIp("127.0.0.1"));
        System.out.println(isCommon("nkanbk_anbka_1455"));
        System.out.println(isIdCard("610502198811157491"));
        System.out.println(isIdCard("610502198811157491", "6105**7491", 4, 4));
    }
}
