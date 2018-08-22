package md5;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class FileMd5 {

    public static String getFileMD5String(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            return "";
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[16 * 1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return bufferToHex(digest.digest());
    }

    public static String getContentMD5(String value) {
        return getContentMD5(value.getBytes());
    }

    public static String getContentMD5(byte[] value) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(value, 0, value.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return bufferToHex(digest.digest());
    }

    ///////////////  add lxz 复原代码 //////////////////////////
    public static String bufferToHex(byte bytes[]) {
        int len = 2 * bytes.length;
        BigInteger bigInt = new BigInteger(1, bytes);
        String md5 = bigInt.toString(16);

        StringBuffer prev = new StringBuffer(32);
        len -= md5.length();
        for (int i = 0; i < len; i++) {
            prev.append("0");
        }

        return prev.toString() + md5;
    }

//	public static String bufferToHex(byte bytes[], int m, int n) {
//		StringBuffer stringbuffer = new StringBuffer(2 * n);
//		int k = m + n;
//		for (int l = m; l < k; l++) {
//			appendHexPair(bytes[l], stringbuffer);
//		}
//		return stringbuffer.toString();
//	}
//
//	public static void appendHexPair(byte bt, StringBuffer stringbuffer) {
//		char c0 = hexDigits[(bt & 0xf0) >> 4];
//		char c1 = hexDigits[bt & 0xf];
//		stringbuffer.append(c0);
//		stringbuffer.append(c1);
//	}
//	
//	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
//		'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    ///////////////  add lxz 复原代码 //////////////////////////

}
