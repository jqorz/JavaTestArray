package encode;

import java.util.Scanner;

/**
 * 仿射加密
 */
public class Affine {

    public static void main(String[] args) {
        System.out.println("请输入待加密的内容(仿射加密)");
        Scanner input = new Scanner(System.in);
        String str1 = input.nextLine();
        String str2 = encrypt(str1);
        System.out.println("加密后的内容为" + str2);
        String str3 = decrypt(str2);
        System.out.println("解密后的内容为" + str3);
        input.close();
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
                a = chars[(eMove(i)) % chars.length];
                break;
            }
        }
        return a;
    }

    // 加密的移位
    private static int eMove(int sourePos) {
        return (7 * sourePos + 3) % 26;
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
                a = chars[(dMove(i) + 26) % chars.length];
                break;
            }
        }
        return a;
    }

    // 解密的移位
    private static int dMove(int sourePos) {
        return move(7) * (sourePos - 3) % 26;
    }

    // 使用二维数组匹配乘法逆元表
    private static int move(int sourcePos) {
        int[][] map = {{1, 1}, {3, 9}, {5, 21}, {7, 15}, {9, 3},
                {11, 19}, {15, 7}, {17, 23}, {19, 11}, {21, 5},
                {23, 17}, {25, 25}};
        for (int[] is : map) {
            if (sourcePos == is[0]) {
                return is[1];
            }
        }

        return sourcePos;
    }

}
