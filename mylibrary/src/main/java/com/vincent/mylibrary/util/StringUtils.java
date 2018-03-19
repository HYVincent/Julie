package com.vincent.mylibrary.util;

import java.util.regex.Pattern;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @project_name StarKangMedical
 * @page_name：com.vincent.mylibrary.util
 * @class describe 文字相关的工具类
 * @date 2017/12/8 11:50
 */

public class StringUtils {

    /**
     * 密码正则表达式 数字和字母匹配
     */
    private static final String REGULAR_EXPRESSION_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$";

    public static boolean checkPassword(String password){
        return Pattern.matches(REGULAR_EXPRESSION_PASSWORD, password);
    }

    /**
     * 从AllContent中截取key关键字后面一位之后的内容，这里从下载地址中截取文件的名称和后缀
     * @param allContent
     * @param key
     * @return
     */
    public static String substringForKey(String allContent,String key){
        int index = allContent.indexOf(key);
        int targetIndex = index+key.length()+1;
        String targetStr = allContent.substring(targetIndex);
        return targetStr;
    }
    public static int string2int (String str) {
        return string2int(str,0);
    }
    public static int string2int (String str, int def) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
        }
        return def;
    }

}
