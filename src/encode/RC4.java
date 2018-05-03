package encode;

/**
 * RC4加密
 */
public class RC4 {
    static String key = "justfortest";// 初始密钥
    static String input = "这是一个用来加密的数据Data";// 明文

    public static void main(String[] args) {

        String str = encrypt(input);
        System.out.println("加密后的内容为--" + string2HexString(str));
        System.out.println("方式一解密后的内容为--" + encrypt(str));
        System.out.println("方式二解密后的内容为--" + encrypt2(str));

    }

    // 字符串转16进制字符串
    private static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    // 对字符串进行加密解密
    private static String encrypt(String str) {
        char[] input = str.toCharArray();
        char[] ouput = new char[input.length];
        int[] s = initKey(key);
        for (int i = 0; i < input.length; i++) {
            ouput[i] = (char) (input[i] ^ getKey(s));
        }
        return String.valueOf(ouput);
    }

    // 对字符串进行方式二解密
    private static String encrypt2(String str) {
        char[] input = str.toCharArray();
        char[] ouput = new char[input.length];
        int[] s = initKey(key);
        int[] ss = s.clone();
        for (int i = 0; i < input.length; i++) {
            ss[i] = getKey(s);
        }
        for (int i = 0; i < input.length; i++) {
            ouput[i] = (char) (input[i] ^ getKey(ss));
        }
        return String.valueOf(ouput);
    }

    // 初始化密钥流
    private static int[] initKey(String key) {
        int[] s = new int[256];
        for (int i = 0; i < s.length; i++) {
            s[i] = i;
        }// 线性填充S
        char[] k = new char[256];
        for (int i = 0; i < k.length; i++) {
            k[i] = key.charAt(i % key.length());
        }// 循环填充K
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + k[i]) % 256;
            int temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }// 交换s[i]和s[j]
        return s;
    }

    // 得到K
    private static int getKey(int[] s) {
        int i = 0, j = 0;
        int k;
        i = (i + 1) % 256;
        j = (j + s[i]) % 256;
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        k = s[(s[i] + s[j]) % 256];
        return k;
    }
}
