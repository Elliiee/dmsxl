package DP;

public class SolutionDP {
    //509 fibonacci 
    public int fib(int n){
        if (n < 2){
            return n;
        }

        int a = 0, b = 1, c = 0;
        for (int i = 1; i < n; i++){
            c = a + b; 
            a = b;
            b = c; 
        }
        return c; 
    }

    //70 climbing stairs 
    public int climbStairs(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1; 
        dp[1] = 1; 
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int climbStairs2(int n){
        if (n <= 2) return n;
        int a = 1, b = 2, sum = 0; 

        for (int i = 3; i <= n; i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return b; 
    }

    //746 
    // 方式一：第一步不支付费用
    public int minCostClimbingStairs(int[] cost){
        int len = cost.length; 
        int[] dp = new int[len + 1];

        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        dp[0] = 0; 
        dp[1] = 0; 

        for (int i = 2; i <= len; i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        return dp[len];
    }

    // 方式二：第一步支付费用
    public int minCostClimbingStairs2(int[] cost){
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        //最后一步，如果是由倒数第二步爬，则最后一步的体力花费可以不用算
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
