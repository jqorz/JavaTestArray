package method;

/**
 * 测试String的split()函数
 */
public class Split {
    public static void main(String[] args) {

        String s = "1";
        String[] ss = s.split(" ");
        for (String sss : ss) {
            System.out.print(sss);
        }

    }
}
