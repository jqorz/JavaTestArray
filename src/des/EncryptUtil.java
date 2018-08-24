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
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return ConvertUtil.bytesToHexString(encryptedData);
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
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(ConvertUtil.hexStringToByte(decryptString));
        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
        String clearText = "111111";  //这里的数据长度必须为8的倍数
        String key = "DATEDU-a";
        String encryptText = encryptDES(clearText, key);
        System.out.println("加密后：" + encryptText);
        String decryptText = decryptDES(encryptText, key);
        System.out.println("解密后：" + decryptText);
    }

}
