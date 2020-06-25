package encode;

import java.io.File;

/**
 * @author j1997
 * @since 2020/6/24
 */
class RC4Manager {
    public static void main(String[] args) {

        RC40.main(args);
        RC41.main(args);
        RC42.main(args);
        RC43.main(args);
        RC44.main(args);
//        RC45.main(args);

        File sourcefile = new File("D:/a/1.jpg");
        File keyFile = new File("D:/a/a.key");


        try {

            byte[] fileByte = FileUtil.file2Byte(sourcefile.getPath());
            byte[] keyByte = FileUtil.file2Byte(keyFile.getPath());
            byte[] newKeyByte = new byte[keyByte.length];
            for (int i = 0; i < newKeyByte.length; i++) {
                newKeyByte[i] = (byte) ~keyByte[i];
            }

            byte[] result4 = RC42.encrypt(fileByte, newKeyByte);
            FileUtil.byte2File(result4, "D:/a/", "2.jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
