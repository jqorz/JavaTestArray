package encode;

/**
 * 混沌加密
 */
public class Logistic {
    private static double u = 3.6;

    private static int[] data = {12, 23, 34, 45, 234, 245, 32, 145, 112};// 明文

    public static void main(String[] args) {
        System.out.print("原数据为:");
        output();
        encrypt(data);
        System.out.print("\n加密后的数据为:");
        output();
        encrypt(data);
        System.out.print("\n解密后的数据为:");
        output();
    }

    //输出
    private static void output() {
        for (int d : data) {
            System.out.print(" " + d);
        }
    }

    //对数组加密
    private static void encrypt(int[] s) {
        double x1 = 0.5;//初始混沌参数
        for (int i = 0; i < s.length; i++) {
            double x= encrypt(x1);
            s[i] = s[i] ^ (int) (x * 255);
            x1 = x;
        }
    }

    //生成密钥子
    private static double encrypt(double s) {
        return u * s * (1 - s);
    }
}
