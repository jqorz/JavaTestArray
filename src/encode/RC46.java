package encode;

/**
 * copyright datedu
 *
 * @author admin
 * @since 2020/6/28
 */
public final class RC46 {


    public static void main(String[] args) {
        String source = "这是一个用来加密的数据Data";
        String key = "justfortest";// 初始密钥

        byte[] keyByte = RC46.getKey(key.getBytes());
        byte[] str =  RC46.encrypt(source.getBytes(), keyByte);
        String hex = FileUtil.byte2HexStr(str);
        System.out.println("RC46 加密后的内容hex为--" + hex);

        byte[] keyByte2 = RC46.getKey(key.getBytes());
        byte[] str2 =RC46.encrypt(str, keyByte2);
        System.out.println("RC46 解密后的内容hex为--" + FileUtil.byte2String(str2));


    }

    public static byte[] getKey(byte[] key) {
        int keylen = 0;
        byte[] S = new byte[256];
        byte[] T = new byte[256];
        byte[] K;

        keylen = key.length;
        int j = 0;

        //Initalize
        for (int i = 0; i < 256; i++) {

            S[i] = (byte) i;
            T[i] = key[i % keylen];

        }
        //Initial permutation
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) & 0xFF;
            Swap(S, i, j);
        }
        return S;
    }

    /**
     * Generation method will output the ciphertext byte array
     *
     * @param stream
     * @return
     */
    //Will encrypt/decrypt using byte array
    public  static  byte[] encrypt(byte[] stream, byte[] S) {


        int i = 0,j = 0, t;
        byte k;

        byte[] result = new byte[stream.length];


        try {
            for (int w = 0; w < stream.length; w++) {
                i = (i + 1) & 0xFF;
                j = (j + S[i]) & 0xFF;
                Swap(S, i, j);
                t = (S[i] + S[j]) & 0xFF;
                k = S[t];
                result[w] = (byte) (stream[w] ^ k);
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Swap Method will swap 2 elements
     *
     * @param s
     * @param t
     */

    public  static  void Swap(byte[] S, int s, int t) {
        byte temp = 0;
        temp = S[s];
        S[s] = S[t];
        S[t] = temp;

    }
}
