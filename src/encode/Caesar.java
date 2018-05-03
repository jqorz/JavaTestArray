package encode;

import java.util.Scanner;

/**
 * 凯撒加密
 */
public class Caesar {

    public static void main(String[] args) {
//        System.out.println("请输入待加密的内容(凯撒加密)");
//        Scanner input = new Scanner(System.in);
//        String str1 = input.nextLine();
//        String str2 = encrypt(str1);
//        System.out.println("加密后的内容为" + str2);
//        String str3 = decrypt(str2);
//        System.out.println("解密后的内容为" + str3);
//        input.close();
        int a = 4;
        int b = 6;
        int c = add(a, b);
        System.out.print(c);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    // 对字符串进行加密
    private static String encrypt(String str) {
        char[] chars = str.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = encrypt(chars[i]);
        }
        return String.valueOf(chars);

    }

    // 对每个字符加密
    private static char encrypt(char a) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (a == chars[i]) {
                a = chars[(i + 3) % chars.length];
                break;
            }
        }
        return a;
    }

    // 对字符串解密
    private static String decrypt(String str) {
        char[] chars = str.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = decrypt(chars[i]);
        }
        return String.valueOf(chars);
    }

    // 对字符进行解密
    private static char decrypt(char a) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (a == chars[i]) {
                a = chars[(i + 23) % chars.length];
                break;
            }
        }
        return a;
    }
}
