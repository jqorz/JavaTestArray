package leecode;

public class LeeCodeJ {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            //只要不为0就往前挪
            if (nums[j] != 0) {
                //i指向的值和j指向的值交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
            j++;
        }
    }
}
