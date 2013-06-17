package cn.edu.nju.util;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-6-13
 * Time: 下午9:16
 * To change this template use File | Settings | File Templates.
 */
public class EncodeUtil {
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
