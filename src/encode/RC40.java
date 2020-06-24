package encode;

/**
 * RC4加密
 */
public class RC40 {

    public static void main(String[] args) {
        String source = "这是一个用来加密的数据Data";
        String key = "justfortest";// 初始密钥
        byte[] str = encrypt(source.getBytes(), key.getBytes());
        String hex = FileUtil.byte2HexStr(str);
        System.out.println("RC40 加密后的内容hex为--" + hex);
//        System.out.println("解密后的内容为--" + FileUtil.byte2String(encrypt(FileUtil.hexStr2Bytes(hex), key.getBytes())));

    }



    // 对字符串进行加密解密
    public static byte[] encrypt(byte[] input, byte[] key) {
        byte[] ouput = new byte[input.length];
        int[] s = initKey(key);
        for (int i = 0; i < input.length; i++) {
            ouput[i] = (byte) (input[i] ^ getKey(s));
        }
        return ouput;
    }

    // 对字符串进行方式二解密
    public static byte[] encrypt2(byte[] input, byte[] key) {
        byte[] ouput = new byte[input.length];
        int[] s = initKey(key);
        int[] ss = s.clone();
        for (int i = 0; i < input.length; i++) {
            ss[i] = getKey(s);
        }
        for (int i = 0; i < input.length; i++) {
            ouput[i] = (byte) (input[i] ^ getKey(ss));
        }
        return ouput;
    }

    // 初始化密钥流
    private static int[] initKey(byte[] key) {
        int[] s = new int[256];
        for (int i = 0; i < s.length; i++) {
            s[i] = i;
        }// 线性填充S
        byte[] k = new byte[256];
        for (int i = 0; i < k.length; i++) {
            k[i] = key[i % key.length];
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
