package org.study.GreedyAlgorithm;

/**
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class BuyAndSellStock {

    /**
     * 方法1：暴力法（超时）
     * 我们需要找出给定数组中两个数字之间的最大差值（即，最大利润）。
     * 此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     */
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    /**
     * 方法2：一次遍历
     * <p>
     * 思路：
     * 1. 每一步都根据当前信息更新可能的最优解（最低买入价和最大利润），确保遍历结束时得到全局最优解。
     */
    public int maxProfit1(int[] prices) {
        int minprice = Integer.MAX_VALUE;// 初始设为 Integer.MAX_VALUE（确保任何股票价格都小于此值）
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                // 更新最低价格
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                // 更新最大利润
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        BuyAndSellStock solution = new BuyAndSellStock();

        // 测试用例1: 正常情况
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("测试用例1:");
        System.out.println("输入: [7, 1, 5, 3, 6, 4]");
        System.out.println("输出: " + solution.maxProfit(prices1));
        System.out.println("预期: 5 (在价格为1时买入，在价格为6时卖出)");

        // 测试用例2: 递减数组
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("\n测试用例2:");
        System.out.println("输入: [7, 6, 4, 3, 1]");
        System.out.println("输出: " + solution.maxProfit(prices2));
        System.out.println("预期: 0 ( prices 递减，无法获得利润)");
    }
}
