package encode;

import java.util.Random;

/**
 * RSA加密
 */
public class RSA {
    public static void main(String[] args) {
        char[] msg = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int[] ms = new int[msg.length];
        for (int i = 0; i < msg.length; i++) {
            ms[i] = (int) msg[i];
        }
        int PP = 7;
        int QQ = 17;
        if (check(PP) && check(QQ)) {
            PSK psk = createKey(PP, QQ);
            System.out.println("公钥e=：" + psk.getPk().getE());
            System.out.println("公钥n=：" + psk.getPk().getN());
            System.out.println("私钥d=：" + psk.getSk().getD());
            System.out.println("私钥n=：" + psk.getSk().getN());
            out("原数据", ms);
            int[] result = encrypt(ms, psk.getPk());
            int[] deResult = decrypt(result, psk.getSk());
            out("加密结果", result);
            out("解密结果", deResult);

        } else {
            System.out.println("P,Q必须都是素数");
        }

    }

    /**
     * 首先选择两个质数p和q，令n=p*q。
     * 令k=(p−1)(q−1)。
     * 选择任意整数e，保证其1<e<k且e与k互质。
     * 取整数d，使得d与e是关于k的模逆
     */

    private static PSK createKey(int p, int q) {
        int n = p * q;
        int k = (p - 1) * (q - 1);
        int e = 2;//产生随机数
        int d;
        while (!check(e, k)) {//使e,k互质
            e = new Random().nextInt(k - 2) + 2;
        }
        d = getMod(e, k);
        System.out.print("e=" + e + "\n");
        System.out.print("k=" + k + "\n");
        return new PSK(new PK(e, n), new SK(d, n));
    }

    /**
     * 检查P,Q是否为素数
     */
    private static boolean check(int pp) {
        for (int i = 2; i <= pp - 1; i++) {
            if (pp % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查m,n是否互质
     */
    private static boolean check(int m, int n) {
        int min = m - n > 0 ? n : m;
        for (int i = min; i >= 1; i--) {
            if (m % i == 0 && n % i == 0) {
                min = i;
                break;
            }
        }
        return min == 1;
    }

    /**
     * RSA算法会提供两个公钥e和n，其值为两个正整数，解密方持有一个私钥d，然后开始加密解密过程过程。
     * 1.首先根据一定的规整将字符串转换为正整数z，例如对应为0到36（此处使用的ASCLL值），转化后形成了一个整数序列。
     * 2.对于每个字符对应的正整数映射值N，计算其加密值M=(N^e)%n. 其中N^e表示N的e次方。
     * 3.解密方收到密文后开始解密，计算解密后的值为(M^d)%n，可在此得到正整数z。
     * 4.根据开始设定的公共转化规则，即可将z转化为对应的字符，获得明文。
     */
    private static int[] encrypt(int[] msg, PK pk) {
        int length = msg.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = getMod(msg[i], pk.getE(), pk.getN());
        }
        return result;
    }


    private static int[] decrypt(int[] msg, SK sk) {
        int length = msg.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = getMod(msg[i], sk.getD(), sk.getN());

        }
        return result;
    }
//            result[i] = new BigDecimal(Math.pow(msg[i], sk.getD()) % sk.getN()).intValue();

    /**
     * 快速幂乘
     * (a^b)对c求余
     *
     * @return 结果
     */
    private static int getMod(int a, int b, int c) {
        int k = 1;
        while (b > 0) {
            if ((b & 1) != 0)     // 作用1：当 b为奇数，则先单独乘一个a
                // 作用：当 b=1时，即 已经乘了 b=b/2=1后，将值赋给k
                k = (k * a) % c;
            a = (a * a) % c;
            b >>= 1;       //a1=a%c                             1=2^0
            // a2=((a%c)*(a%c))%c                2=2^1
            // a3=((a%c*a%c)%c*(a%c*a%c)%c)      4=2^2
            //由此，可知 b=b/2 ,每次的 a 的个数为上一次的 2倍
        }
        return k;
    }

    /**
     * 3*7= 1 mod(20):3和7互为20的模逆
     *
     * @param e     3
     * @param model 20
     * @return 7
     */
    private static int getMod(int e, int model) {
        int d = 0;
        int i;
        int over = e;
        for (i = 1; i < model; ) {
            over = over % model;
            if (over == 1) {
                d = i;
                return d;
            } else {
                if (over + e <= model) {
                    do {
                        i++;
                        over += e;
                    } while (over + e <= model);
                } else

                {
                    i++;
                    over += e;
                }
            }
        }
        return d;
    }

    /**
     * 打印
     */
    private static void out(String tag, int[] a) {
        System.out.print(tag + ":");
        for (int aa : a) {
            System.out.print(" " + aa);
        }

        System.out.println();
    }

    private static class PK {
        private int e;
        private int n;

        public PK(int ee, int nn) {
            e = ee;
            n = nn;
        }

        public int getE() {
            return e;
        }

        public int getN() {
            return n;
        }

    }

    private static class PSK {
        private PK pk;
        private SK sk;

        PSK(PK pk, SK sk) {
            this.pk = pk;
            this.sk = sk;
        }

        PK getPk() {
            return pk;
        }

        public SK getSk() {
            return sk;
        }
    }

    private static class SK {
        private int d;
        private int n;

        SK(int dd, int nn) {
            d = dd;
            n = nn;
        }

        int getD() {
            return d;
        }

        int getN() {
            return n;
        }

    }
}
