package encode;

/**
 * Created by alina on 12/5/17.
 */


public class RC45 {


    public static byte[] encrypt(byte[] input, byte[] key) {

        byte[] output = new byte[input.length];
        for (int k = 0; k < input.length; k++) {
            output[k] = (byte) (input[k] ^ rc4_output(getBox(key)));
        }
        return output;

    }

    public static void main(String[] args) {
        String source = "这是一个用来加密的数据Data";
        String key = "justfortest";// 初始密钥

        byte[] str = new byte[0];
        str = encrypt(source.getBytes(), key.getBytes());
        String hex = FileUtil.byte2HexStr(str);
        System.out.println("RC45 加密后的内容hex为--" + hex);
//        System.out.println("解密后的内容为--" + FileUtil.byte2String(encrypt(FileUtil.hexStr2Bytes(hex), key.getBytes())));


    }

    static byte[] getBox(byte[] key) {
        byte[] box = new byte[256];
        int i, j;
        byte temp;
        for (i = 0; i != 256; ++i)
            box[i] = (byte) i;

        for (i = j = 0; i != 256; ++i) {
            j = (j + key[i % key.length] + box[i]) % 256;
            temp = box[i];
            box[i] = box[j];
            box[j] = temp;
        }
        return box;
    }

    static byte rc4_output(byte[] box) {
        byte temp;
        int i = 0, j = 0;

        i = (i + 1) % 256;
        j = (j + box[i]) % 256;

        temp = box[j];
        box[j] = box[i];
        box[i] = temp;

        return box[(temp + box[j]) % 256];
    }

}