package encode;

import java.security.InvalidKeyException;

/**
 * @author j1997
 * @since 2020/6/24
 */
public class RC44 {

    /**
     * Initialize the RC40 cipher.
     *
     * @param key The key argument should RC40 key, at least 1 byte and at most
     *            256 bytes.
     */
    public static short[] init(byte[] key) throws InvalidKeyException {
        short[] s = new short[256];
        int keylen = key.length;
        short[] t = new short[256];
        if (keylen < 1 || keylen > 256) {
            /* Key Size Error */
            throw new InvalidKeyException("The key size is not fair.");
        }
        for (int i = 0; i < 256; i++) {
            s[i] = (short) i; // This is fine, No need to cast.
            t[i] = (short) ((key[i % keylen] + 256) % 256);
        }
        int j = 0;
        short tmp;
        for (int i = 0; i < 256; i++) {
            j = (j + (s[i] + t[i])) & 0xff;
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
        return s;
    }

    /**
     * XorKeyStream sets dst to the result of XORing src with the key stream.
     * Dst and src May be the same slice but otherwise should not overlap.
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws InvalidKeyException {
        short[] s = init(key);
        int i = 0, j = 0;
        short tmp;
        byte[] dst = new byte[src.length];
        for (int pos = 0; pos < src.length; pos++) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            dst[pos] = (byte) (src[pos] ^ s[(s[i] + s[j]) % 256]);
        }
        return dst;
    }

    public static void main(String[] args) {
        String source = "这是一个用来加密的数据Data";
        String key = "justfortest";// 初始密钥
        try {
            byte[] str = new byte[0];
            str = encrypt(source.getBytes(), key.getBytes());
            String hex = FileUtil.byte2HexStr(str);
            System.out.println("RC44 加密后的内容hex为--" + hex);

            byte[] str2= encrypt(str,key.getBytes());
            System.out.println("RC44 解密后的内容hex为--" + FileUtil.byte2String(str2));

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }
}