package method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jqorz on 2017/10/9.
 * 测试正则匹配
 */
public class Regular {
    public static void main(String[] args) {
        String input = "mingxing/10833";
        if (!input.endsWith("/"))
            input = input + "/";
        String pattern = "(\\D*)/(\\d+)/";
        if (!input.matches(pattern)) {
            System.out.print("not match");
        }else {
            System.out.print("match");
        }
    }
}
