package org.study.DoublePoint;

public class MoveZeroes {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 解决思路：
     * 1. 创建两个指针（其实就是数据标识），left和right都从0索引开始
     * 2. 遍历数组，如果当前元素不为0，则将当前元素与left索引的元素交换位置，同时left指针右移
     * 3. 思路是将不是0的数往左移交换。
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void moveZeroes1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 如果当前元素是 0
            if (nums[i] == 0) {
                // 就从 i+1 的位置开始，向后寻找第一个非零元素
                for (int j = i + 1; j < n; j++) {
                    // 找到了非零元素
                    if (nums[j] != 0) {
                        // 交换它们
                        swap(nums, i, j);
                        // 交换完成后，内层循环就可以停止了，因为我们只需要找到第一个非零数
                        break;
                    }
                }
            }
        }
    }


    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        MoveZeroes s = new MoveZeroes();
        s.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num+",");
        }
    }
}
