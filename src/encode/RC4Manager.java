package encode;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author j1997
 * @since 2020/6/24
 */
class RC4Manager {
    public static void main(String[] args) {

//        RC40.main(args);
//        RC41.main(args);
        RC42.main(args);//可以用
//        RC43.main(args);
        RC44.main(args);//可以用
//        RC45.main(args);
        RC46.main(args);//可以用

        File sourcefile = new File("D:/a/1.jpg");
        File keyFile = new File("D:/a/a.key");


        try {

            char[] rc4key = {
                    0x2c, 0xb6, 0xa1, 0xe7, 0xe1, 0x0c, 0x50, 0x02,
                    0xa5, 0xde, 0xae, 0x7f, 0xe6, 0x05, 0xbd, 0x90,
            };

            byte[] sourceFileByte = FileUtil.file2Byte(sourcefile.getPath());


            byte[] keyFileByte = FileUtil.file2Byte(keyFile.getPath());
            byte[] newKeyFileByte = new byte[keyFileByte.length];
            for (int i = 0; i < newKeyFileByte.length; i++) {
                newKeyFileByte[i] = (byte) ~keyFileByte[i];
            }


//            byte[] keyByte = RC46.getKey(getBytes(rc4key));
//            byte[] newKeyByte = new byte[keyByte.length];
//            for (int i = 0; i < newKeyByte.length; i++) {
//                newKeyByte[i] = (byte) ~keyByte[i];
//            }


            FileUtil.byte2File(RC46.encrypt(sourceFileByte, newKeyFileByte), "D:/a/", "46.jpg");

//            FileUtil.byte2File(RC40.encrypt(fileByte, newKeyByte), "D:/a/", "40.jpg");
//            FileUtil.byte2File(RC41.encrypt(fileByte, newKeyByte), "D:/a/", "41.jpg");
//            FileUtil.byte2File(RC42.encrypt(fileByte, newKeyByte), "D:/a/", "42.jpg");
//            FileUtil.byte2File(RC43.encrypt(fileByte, newKeyByte), "D:/a/", "43.jpg");
//            FileUtil.byte2File(RC44.encrypt(fileByte, newKeyByte), "D:/a/", "44.jpg");
//
//            FileUtil.byte2File(new RC47(newKeyByte).encrypt(sourceFileByte),"D:/a/", "47.jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }
}
