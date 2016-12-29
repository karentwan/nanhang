package cn.karent.nanhang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wan on 2016/12/29.
 * 判断时候是中文
 */
public class TextUtil {

    private  static Pattern mPattern = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 判断是否为中文
     * @param s
     * @return
     */
    public static boolean isChinese(String s) {
        Matcher m = mPattern.matcher(s);
        if( m.find() ) {
            return true;
        }
        return false;
    }

    /**
     * 测量中英文混合的字符串
     * @param str
     * @return
     */
    public static int measureChineseMixLength(String str) {
        int length = 0;
        for(int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i + 1);
            if( isChinese(s) )
                length += 2;
            else
                length += 1;
        }
        return length;
    }


}
