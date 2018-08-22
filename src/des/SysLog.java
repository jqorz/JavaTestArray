package des;

/**
 * @author jqorz
 * @since 2018/8/22.
 */

public class SysLog {
    public static void i(Object content) {
        System.out.println(content.toString());
    }

    public static void i(String tag, Object content) {
        System.out.println(tag + " " + content.toString());
    }
}
