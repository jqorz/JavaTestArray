package encode;

public class RC42 {

    public static void main(String[] args) {
        String source = "这是一个用来加密的数据Data";
        String key = "justfortest";// 初始密钥
        byte[] str = encrypt(source.getBytes(), key.getBytes());
        String hex = FileUtil.byte2HexStr(str);
        System.out.println("RC42 加密后的内容hex为--" + hex);
//        System.out.println("解密后的内容为--" + FileUtil.byte2String(encrypt(FileUtil.hexStr2Bytes(hex), key.getBytes())));

    }



    private static byte[] initKey(byte[] b_key) {
        byte[] state = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (b_key.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % b_key.length;
        }
        return state;
    }




    private static byte[] encrypt(byte[] input, byte[] mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);
        int xorIndex;
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;
            y = ((key[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }


}