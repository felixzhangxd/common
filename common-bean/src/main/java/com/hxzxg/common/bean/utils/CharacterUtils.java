package com.hxzxg.common.bean.utils;

import com.hxzxg.common.bean.constant.ASCII;

/**
 * @author felix
 */
public final class CharacterUtils {
    private CharacterUtils() {}

    /** 判断是否是大写字母 */
    public static boolean isUpperCase(byte b) {
        return 0x41 <= b && b <= 0x5A;
    }
    /** 判断是否是大写字母 */
    public static boolean isUpperCase(char c) {
        return 0x41 <= c && c <= 0x5A;
    }
    /** 判断是否是小写字母 */
    public static boolean isLowerCase(byte b) {
        return 0x61 <= b && b <= 0x7A;
    }
    /** 判断是否是小写字母 */
    public static boolean isLowerCase(char c) {
        return 0x61 <= c && c <= 0x7A;
    }
    /** 小写字母 转 大写字母 */
    public static byte toUpperCase(byte b) {
        return CharacterUtils.isLowerCase(b) ? (b -= 0x20) : b;
    }
    /** 小写字母 转 大写字母 */
    public static char toUpperCase(char c) {
        return CharacterUtils.isLowerCase(c) ? (c -= 0x20) : c;
    }
    /** 大写字母 转 小写字母 */
    public static byte toLowerCase(byte b) {
        return CharacterUtils.isUpperCase(b) ? (b += 0x20) : b;
    }
    /** 大写字母 转 小写字母 */
    public static char toLowerCase(char c) {
        return CharacterUtils.isUpperCase(c) ? (c += 0x20) : c;
    }
    /** 小写字母 转 大写字母 */
    public static void toUpperCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = CharacterUtils.toUpperCase(bs[i]);
        }
    }
    /** 小写字母 转 大写字母 */
    public static void toUpperCase(char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharacterUtils.toUpperCase(cs[i]);
        }
    }
    /** 大写字母 转 小写字母 */
    public static void toLowerCase(byte[] bs) {
        for (int i = 0; i < bs.length; i++) {
            bs[i] = CharacterUtils.toLowerCase(bs[i]);
        }
    }
    /** 大写字母 转 小写字母 */
    public static void toLowerCase(char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            cs[i] = CharacterUtils.toLowerCase(cs[i]);
        }
    }
    /** 首字母 转 大写字母 */
    public static void toUpperCaseFirst(byte[] bs) {
        bs[0] = CharacterUtils.toUpperCase(bs[0]);
    }
    /** 首字母 转 大写字母 */
    public static void toUpperCaseFirst(char[] cs) {
        cs[0] = CharacterUtils.toUpperCase(cs[0]);
    }
    /** 首字母 转 大写字母 */
    public static String toUpperCaseFirst(String s) {
        byte[] bs = s.getBytes();
        CharacterUtils.toUpperCaseFirst(bs);
        return new String(bs);
    }
    /** 首字母 转 小写字母 */
    public static void toLowerCaseFirst(byte[] bs) {
        bs[0] = CharacterUtils.toLowerCase(bs[0]);
    }
    /** 首字母 转 小写字母 */
    public static void toLowerCaseFirst(char[] cs) {
        cs[0] = CharacterUtils.toLowerCase(cs[0]);
    }
    /** 首字母 转 小写字母 */
    public static String toLowerCaseFirst(String s) {
        byte[] bs = s.getBytes();
        CharacterUtils.toLowerCaseFirst(bs);
        return new String(bs);
    }
    /** 下划线命名 转 大驼峰命名 例：user_name => UserName */
    public static String toCamelUpperCase(String underlineCase) {
        byte[] bs = underlineCase.getBytes();
        CharacterUtils.toLowerCase(bs);
        int index = 0;
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = bs[i] == ASCII.UL ? CharacterUtils.toUpperCase(bs[++i]) : bs[i];
        }
        CharacterUtils.toUpperCaseFirst(bs);
        return new String(bs, 0, index);
    }
    /** 下划线命名 转 小驼峰命名 例：user_name => userName */
    public static String toCamelLowerCase(String underlineCase) {
        byte[] bs = underlineCase.getBytes();
        CharacterUtils.toLowerCase(bs);
        int index = 0;
        for (int i = 0; i < bs.length; i++) {
            bs[index++] = bs[i] == ASCII.UL ? CharacterUtils.toUpperCase(bs[++i]) : bs[i];
        }
        return new String(bs, 0, index);
    }
    /** 驼峰命名 转 大下划线命名 例：userName => USER_NAME */
    public static String toUnderlineUpperCase(String camelCase) {
        byte[] bs = camelCase.getBytes();
        CharacterUtils.toLowerCaseFirst(bs);
        byte[] underlineCases = new byte[bs.length * 2];
        int index = 0;
        for (byte b : bs) {
            if (CharacterUtils.isUpperCase(b)) {
                underlineCases[index++] = ASCII.UL;
            }
            underlineCases[index++] = CharacterUtils.toUpperCase(b);
        }
        return new String(underlineCases, 0, index);
    }
    /** 驼峰命名 转 小下划线命名 例：userName => user_name  */
    public static String toUnderlineLowerCase(String camelCase) {
        byte[] bs = camelCase.getBytes();
        CharacterUtils.toLowerCaseFirst(bs);
        byte[] underScoreCases = new byte[bs.length * 2];
        int index = 0;
        for (byte b : bs) {
            if (CharacterUtils.isUpperCase(b)) {
                underScoreCases[index++] = ASCII.UL;
            }
            underScoreCases[index++] = CharacterUtils.toLowerCase(b);
        }
        return new String(underScoreCases, 0, index);
    }
    /** 属性名称 转 get方法名 例：userName => getUserName */
    public static String toGetMethod(String fieldName) {
        return "get" + CharacterUtils.toUpperCaseFirst(fieldName);
    }
    /** 属性名称 转 set方法名 例: userName => setUserName */
    public static String toSetMethod(String fieldName) {
        return "set" + CharacterUtils.toUpperCaseFirst(fieldName);
    }

    /**
     * @param content 重复内容
     * @param separator 分隔符
     * @param times 重复次数
     * @return
     */
    public static String repeat(String content, String separator, int times) {
        int length = (content.length() + separator.length()) * times - separator.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < times; i++) {
            if(i != 0) {
                builder.append(separator);
            }
            builder.append(content);
        }
        return builder.toString();
    }
}
