package org.study.hash;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /***
     * 两数之和
     * @param nums 给定的数组
     * @param target 目标值
     * @return 返回两个数的索引
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 11, 15,2};
        int target = 9;
        Solution s = new Solution();
        int[] res = s.twoSum(nums, target);
        if (res.length == 2) {
            System.out.printf("找到结果：%d 和 %d，它们的索引分别为 %d 和 %d。%n",
                    nums[res[0]], nums[res[1]], res[0], res[1]);
        } else {
            System.out.println("未找到符合条件的两个数。");
        }
    }
}
