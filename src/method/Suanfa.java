package method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jqorz
 * @since 2018/11/23.
 * //7.有数组[1,3,5,9,0]，请编写排序算法，函数参数支持顺序或倒序输出
 * //8.请寻找整数n以内的被三整除的数，且该数每个位的数字都相同。
 */

class Suanfa {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 9, 0};
        sortNum(a, false);
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + "\t");
        }
        System.out.println(getNum(999));

    }

    public static void sortNum(int[] a, boolean shunxu) {
        for (int j = 0; j < a.length - 1; j++) {
            for (int i = 0; i < a.length - 1 - j; i++) {
                if (shunxu ? (a[i] > a[i + 1]) : (a[i] < a[i + 1])) {
                    int temp;
                    temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public static String getNum(int max) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            if (i % 3 != 0) {
                continue;
            }
            char[] chars = String.valueOf(i).toCharArray();
            boolean r = true;
            if (chars.length < 2)
                continue;
            for (int j = 0; j < chars.length; j++) {
                for (int k = j + 1; k < chars.length; k++) {
                    if (chars[j] != chars[k]) {
                        r = false;
                    }
                }
            }
            if (r) {
                data.add(i);
            }
        }
        return data.toString();
    }
}
