package string;

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
}
