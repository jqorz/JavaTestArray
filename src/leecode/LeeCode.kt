package leecode

class LeeCode {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *示例:
     *输入: [0,1,0,3,12]
     *输出: [1,3,12,0,0]
     */
    fun moveZeroes(nums: IntArray) {
        var i = 0
        var j = 0
        do {
            if (nums[j] != 0) {
                //使用异或交换值时，注意要判断不等，如果相等，异或会为0
                if (nums[i] != nums[j]) {
                    nums[i] = nums[i] xor nums[j]
                    nums[j] = nums[j] xor nums[i]
                    nums[i] = nums[i] xor nums[j]
                }
                i++
            }
            j++
        } while (j < nums.size)
    }
}