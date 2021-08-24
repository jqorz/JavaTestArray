package hash;

/**
 * @author j1997
 * @since 2021/8/24
 */

import java.util.HashMap;



public class HashTest {

    public static void main(String[] args) {
        Student zhangsan = new Student("zhangsan", "男", 25);
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(1, zhangsan);
        HashMap<Integer, Object> cloneMap = (HashMap<Integer, Object>) map.clone();
        System.out.println("*************************不做改变***********************************");
        System.out.println("未改变之前,     map的值:" + map.toString());
        System.out.println("未改变之前,cloneMap的值:" + cloneMap.toString());
        System.out.println("map和cloneMap是否指向同一内存地址:" + (map == cloneMap));
        System.out.println("map和cloneMap中存储的student是否指向同一内存地址:" + (map.get(1) == cloneMap.get(1)));
        //对cloneMap中的值进行改变，看是否能影响到map
        Student cloneItem = (Student) cloneMap.get(1);
        cloneItem.setSex("女");
        System.out.println("*************************对clonemap中的值做出修改****************************");
        System.out.println("改变之后,cloneMap的值:" + cloneMap.toString());
        System.out.println("改变之后,     map的值:" + map.toString());

        System.out.println("*************************对map新增**********************************");
        Student newItem = new Student("lisi", "男", 18);
        cloneMap.put(2, newItem);
        System.out.println("改变之后,cloneMap的值:" + cloneMap.toString());
        System.out.println("改变之后,     map的值:" + map.toString());
    }
}
