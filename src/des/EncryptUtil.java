package des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jqorz on 2017/11/6.
 */

public class EncryptUtil {
    /**
     * 加密数据
     *
     * @param encryptString 注意：这里的数据长度只能为8的倍数
     * @param encryptKey    密钥
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return bytesToHexString(encryptedData);
    }

    /**
     * 自定义一个key
     *
     * @param keyRule 密钥
     */
    public static byte[] getKey(String keyRule) {
        Key key;
        byte[] keyByte = keyRule.getBytes();
        // 创建一个空的八位数组,默认情况下为0
        byte[] byteTemp = new byte[8];
        // 将用户指定的规则转换成八位数组
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, "DES");
        return key.getEncoded();
    }

    /***
     * 解密数据
     * @param decryptString 已加密字符串
     * @param decryptKey 密钥
     * @return 解密后数据
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(hexStringToByte(decryptString));
        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
        String clearText = "111111";  //这里的数据长度必须为8的倍数
        String key = "DATEDU-a";
//        String encryptText ="6BE2E060EFAFF419";
        String encryptText = encryptDES(clearText, key);
        System.out.println("加密后：" + encryptText);
        String decryptText = decryptDES(encryptText, key);
        System.out.println("解密后：" + decryptText);
    }

    @SuppressWarnings("Duplicates")
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }
    private static byte toByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }
    @SuppressWarnings("Duplicates")
    private static String bytesToHexString(byte[] bArray) {
        if (bArray == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp);
        }
        return sb.toString();
    }
}
