package method;

import java.util.LinkedList;

/**
 * Created by jqorz on 2017/11/8.
 * 测试List插入数据
 */
//[0, 1, 2, 10, 3, 4, 20, 5, 6, 7, 8, 9]
public class ListTest {
    public static void main(String[] args) {

        LinkedList<Integer> set = new LinkedList<>();
        for (int j = 0; j < 10; j++) {
            set.add(j);
        }
        set.add(3, 10);
        set.add(6, 20);
        System.out.print(set.toString());


    }
}
