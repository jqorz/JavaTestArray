package code;

import java.util.Scanner;

/**
 * @author jqorz
 * @since 2018/8/21.
 * 有股神吗？
 * 有，小赛就是！
 * 经过严密的计算，小赛买了一支股票，他知道从他买股票的那天开始，股票会有以下变化：第一天不变，以后涨一天，跌一天，涨两天，跌一天，涨三天，跌一天...依此类推。
 * 为方便计算，假设每次涨和跌皆为1，股票初始单价也为1，请计算买股票的第n天每股股票值多少钱？
 * 输入 1 2 3 4 5 输出 1 2 1 2 3
 */

class gushen {
    public static void main(String[] args) {
        int day = new Scanner(System.in).nextInt();
        int i = 0, k = 2, j = 2;
        while (k < day) {
            i = i + 2;
            j = j + 1;
            k = k + j;
        }

        System.out.println(day - i);
    }
}
