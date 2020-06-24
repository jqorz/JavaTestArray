package method;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试String的split()函数
 */
public class Split {
    static String weekString = "5,7-11,13-21双";

    public static void main(String[] args) {

        String[] oneString = weekString.split(",");
        List<Integer> weeks = new ArrayList<>();

        for (String anOneString : oneString) {
            //如果包含"-"，比如1-12,5-11单
            if (anOneString.contains("-")) {//如果周数是连续的，比如1-12
                int startWeek = Integer.parseInt(anOneString.substring(0, anOneString.indexOf("-")));
                int endWeek = Integer.parseInt(anOneString.substring(anOneString.indexOf("-") + 1, anOneString.replaceAll("双", "").replaceAll("单", "").length()));
                for (int j = 0; j <= endWeek - startWeek; j++) {
                    int currentWeek = startWeek + j;
                    if (anOneString.contains("单")) {//如果包含"单"，滤掉双周课程
                        if (currentWeek % 2 == 0) continue;
                    }
                    if (anOneString.contains("双")) {//如果包含"双"，滤掉单周课程
                        if (currentWeek % 2 == 1) continue;
                    }
                    weeks.add(currentWeek);
                }


            } else {
                weeks.add(Integer.parseInt(anOneString));
            }
        }
        int[] ww = new int[weeks.size()];//用于保存课程的上课周数
        for (int i = 0; i < ww.length; i++) {
            ww[i] = weeks.get(i);
            System.out.print(ww[i]+" ");
        }
    }
}
