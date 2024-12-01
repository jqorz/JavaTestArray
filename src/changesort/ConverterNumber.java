package changesort;


import java.util.HashMap;
import java.util.Set;

/**
 * @author <a href="https://github.com/motcs">motcs</a>
 * @since 2022-05-18 18:39:58 星期三
 */
public class ConverterNumber {

    private static final HashMap<Character, Integer> chineseMap = new HashMap<>() {{
        put('零', 0);
        put('一', 1);
        put('二', 2);
        put('三', 3);
        put('四', 4);
        put('五', 5);
        put('六', 6);
        put('七', 7);
        put('八', 8);
        put('九', 9);
        put('十', 10);
        put('百', 100);
        put('千', 1000);
        put('万', 10000);
        put('亿', 100000000);
    }};
    private static final Set<Character> CHINESE_NUMBER = chineseMap.keySet();


    public static String chineseToNumber(String chinese) {
        if (chinese == null || chinese.isEmpty()) {
            return "000";
        }
        //判断是否是纯中文汉字，不是则直接返回00
        if (!isChineseNum(chinese)) {
            return "000";
        }
        int number = 0;
        int temp = 0;
        for (int i = 0; i < chinese.length(); i++) {
            int current = chineseMap.get(chinese.charAt(i));
            if (current < 10) {
                temp += current;
            } else {
                // 处理十百千的情况
                if (current == 10 || current == 100 || current == 1000) {
                    if (temp == 0) {
                        temp = 1;
                    }
                    number += temp * current;
                } else { // 处理其他情况
                    number += temp;
                    number += current;
                }
                temp = 0;
            }
        }
        number += temp;
        return append(String.valueOf(number));
    }

    /**
     * 判断传入的字符串是否全是汉字数字
     *
     * @param chineseStr 中文
     * @return 是否全是中文数字
     */
    public static boolean isChineseNum(String chineseStr) {
        for (char c : chineseStr.toCharArray()) {
            if (!CHINESE_NUMBER.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private static String append(String num) {
        if (num != null) {
            return num.length() == 2 ? num : "0" + num;
        }
        return "00";
    }

}

