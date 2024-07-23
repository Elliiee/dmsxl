package DP;

import java.util.Arrays;

public class Knapsack01 {
    public static void main(String[] args){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4; 
        testWeightBagProblem(weight, value, bagSize);
        //testWeightBagProblem2(weight, value, bagSize);
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){
        int goods = weight.length;
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        //so only need to fill the value[0] scenarios 
        for (int j = weight[0]; j <= bagSize; j++){
            dp[0][j] = value[0];
        }

        for (int i = 1; i < weight.length; i++){ //i = 0 and i = 0 initialized already
            for (int j = 1; j <= bagSize; j++){
                if (j < weight[i]){
                    dp[i][j] = dp[i-1][j]; //bag too small, not able to fit the current item
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        //print out result 
        for (int[] arr : dp)
            System.out.println(Arrays.toString(arr));
        
    }


    /**
     * 初始化 dp 数组做了简化(给物品增加冗余维)。这样初始化dp数组，默认全为0即可。
     * dp[i][j] 表示从下标为[0 - i-1]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
     * 其实是模仿背包重量从 0 开始，背包容量 j 为 0 的话，即dp[i][0]，无论是选取哪些物品，背包价值总和一定为 0。
     * 可选物品也可以从无开始，也就是没有物品可选，即dp[0][j]，这样无论背包容量为多少，背包价值总和一定为 0。
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    //这个让代码比较简洁，但是理解起来挺绕的，我还是用前面那种更好理解的方法
    public static void testWeightBagProblem2(int[] weight, int[] value, int bagSize){
        int goods = weight.length; 
        int[][] dp = new int[goods + 1][bagSize + 1];

        for (int i = 1; i <= goods; i++){
            for (int j = 1; j <= bagSize; j++){
                if (j < weight[i-1]){ //i - 1 对应物品 i
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]] + value[i-1]);
                }
            }
        }

        for (int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
    }

}
