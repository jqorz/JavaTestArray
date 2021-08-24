package hash;

/**
 * @author j1997
 * @since 2021/8/24
 */

import java.util.HashMap;


public class HashTest2 {

    public static void main(String[] args) {
        HashMap source = new HashMap();
        source.put("key1", "value1");
        source.put("key2", "value2");

        HashMap targetMap = (HashMap) source.clone();

        printInfo(source, targetMap);


        System.out.println("---------------- change  targetMap key1 value： targetMap.put(\"key1\", \"modify\") ----------------");
        targetMap.put("key1", "modify");

        printInfo(source, targetMap);
    }

    private static void printInfo(HashMap source, HashMap targetMap) {
        for (Object key : source.keySet()) {
            System.out.println("source " + key + " : " + source.get(key));
        }

        for (Object key : targetMap.keySet()) {
            System.out.println("targetMap " + key + " : " + targetMap.get(key));
        }
    }

}
