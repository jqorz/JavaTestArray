package list;

import utils.GsonUtil;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf(i));
        }
//        for (int i = 0; i < list.size(); i++) {
//            if (i==3){
//                list.add(3,"s");
//            }
//        }
        for (String s:list) {
            if (s.equals("3")){
                list.add("s");
            }
        }
        System.out.println(GsonUtil.jsonCreate(list));
    }
}
