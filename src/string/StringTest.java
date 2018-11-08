package string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jqorz
 * @since 2018/8/21.
 */

class StringTest {
    public static int compare(String str1, String str2, int start) {
        if (start < 0) return -1;
        if (str1 == null || str2 == null) return -1;
        int len = str1.length() > str2.length() ? str2.length() : str1.length();
        if (start >= len) return -1;
        char c1, c2;
        int okLen = -1;
        for (int i = start; i < len; i++) {
            c1 = str1.charAt(i);
            c2 = str2.charAt(i);
            if (c1 == c2) {
                okLen++;
            } else {
                return okLen;
            }
        }
        return okLen;
    }

    public static void charset() {
        String s = "我爱你";
        try {
            String s1 = new String(s.getBytes("utf-8"), "gb2312");
            System.out.println("s1 = " + s1);
            String s2 = new String(s1.getBytes("gb2312"), "utf-8");
            System.out.println("s2 = " + s2);
            String s3 = new String(s2.getBytes(), "utf-8");
            System.out.println("s3 = " + s3);
            String s4 = new String(s.getBytes(), "gb2312");
            System.out.println("s4 = " + s4);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把26个字母4个一组，竖着排
     */
    public static void charAll() {
        String[] source = new String[28];
        char c = 'a';
        for (int i = 0; i < 28; i++) {
            if (i == 20) {
                source[i] = "0";
                continue;
            }
            if (i == 27) {
                source[i] = "0";
                continue;
            }
            source[i] = c + "";
            c++;
        }
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (i + 7 * j < 28) {
                    data.add(source[i + 7 * j]);
                }
            }
        }
        System.out.println(data);
    }

    public static void main(String[] args) {
        charAll();
    }
}
