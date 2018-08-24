package des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import aes.Base64;

/**
 * @author jqorz
 * @since 2018/8/24.
 */

class DesCyUtils2 {
    /**
     * DES加密(加密登录账号和密码)
     * 点击头像授权登陆时所用的加密方法
     *
     * @param data 需要加密的数据
     * @param key  加密所用的字符串
     */
    public static String desEncrypt(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("/DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFatory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFatory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
            byte[] content = cipher.doFinal(data.getBytes("UTF-8"));
            return Base64.encodeToString(content, Base64.DEFAULT);
        } catch (Exception ex) {
            return "";
        }
    }

//    public static void main(String[] args) throws Exception {
//        String clearText = "111111";  //这里的数据长度必须为8的倍数
//        String key = "DATEDU-a";
//        String encryptText = desEncrypt(clearText, key);
//        System.out.println("加密后：" + encryptText);
//        String decryptText = desDecrypt(encryptText, key);
//        System.out.println("解密后：" + decryptText);
//    }
}
