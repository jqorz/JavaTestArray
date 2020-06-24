package encode;

public class RC43 {

	public static byte[] encrypt(byte[] iInputChar, byte[] aKey) {
		int[] iS = new int[256];
		byte[] iK = new byte[256];

		for (int i = 0; i < 256; i++)
			iS[i] = i;

		int j = 1;

		for (int i = 0; i < 256; i++) {
			iK[i] =   aKey[((i % aKey.length))];
		}

		j = 0;

		for (int i = 0; i < 255; i++) {
			j = (j + iS[i] + iK[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
		}

		int i = 0;
		j = 0;
		byte[] iOutputChar = new byte[iInputChar.length];
		for (int x = 0; x < iInputChar.length; x++) {
			i = (i + 1) % 256;
			j = (j + iS[i]) % 256;
			int temp = iS[i];
			iS[i] = iS[j];
			iS[j] = temp;
			int t = (iS[i] + (iS[j] % 256)) % 256;
			int iY = iS[t];
			byte iCY = (byte) iY;
			iOutputChar[x] = (byte) (iInputChar[x] ^ iCY);
		}

		return iOutputChar;

	}

	public static void main(String[] args) {
		String source = "这是一个用来加密的数据Data";
		String key = "justfortest";// 初始密钥
		byte[] str = encrypt(source.getBytes(), key.getBytes());
		String hex = FileUtil.byte2HexStr(str);
		System.out.println("RC43 加密后的内容hex为--" + hex);
//        System.out.println("解密后的内容为--" + FileUtil.byte2String(encrypt(FileUtil.hexStr2Bytes(hex), key.getBytes())));

	}
}