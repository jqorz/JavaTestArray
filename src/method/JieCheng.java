package method;

import java.math.BigInteger;

/**
 * Created by jqorz on 2017/9/14.
 * 求大数阶乘
 */
public class JieCheng {
    public static void main(String[] args) {
        BigInteger sum = new BigInteger("0");
        for (int i = 1; i <= 100; i++) {
            sum = sum.add(cal(BigInteger.valueOf(i)));
        }
        System.out.print(sum);
    }

    private static BigInteger cal(BigInteger i) {
        if (i.equals(BigInteger.ONE)) {
            return i;
        }
        return i.multiply(cal(i.subtract(BigInteger.ONE)));

    }

}
