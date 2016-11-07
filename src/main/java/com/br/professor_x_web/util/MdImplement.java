package com.br.professor_x_web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD 消息摘要组建
 *
 * @author xin.cao@100credit.com
 */
public class MdImplement {

    /**
     * MD5消息摘要（JDK实现）
     *
     * @param input
     * @return
     */
    public static String encodeMD5To32(String input) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        md5.update(input.getBytes());
        byte[] b = md5.digest();
        int i;
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }
}
