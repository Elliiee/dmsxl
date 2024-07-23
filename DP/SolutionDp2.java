package DP;

public class SolutionDp2 {
    //knapsack problems 01 

    //416. Partition Equal Subset Sum
    /*
     * Given an integer array nums, return true if you can partition the array
     * into two subsets such that the sum of the elements in both subsets is equal
     * or false otherwise. 
     * 题目难易：中等
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
     */
    //只要找到集合里能够出现 sum / 2 的子集总和，就算是可以分割成两个相同元素和子集了。
    //本题是可以用回溯暴力搜索出所有答案的，但最后超时了，也不想再优化了，放弃回溯，直接上01背包吧。
    //要明确本题中我们要使用的是01背包，因为元素我们只能用一次。
    /*
     * 1. 背包的体积为sum / 2 -- capacity = sum / 2 
     * 2. 背包要放入的商品（集合里的元素）重量为 元素的数值 weight[i]，价值也为元素的数值 value[i]
     * 3. 背包如果正好装满，说明找到了总和为 sum / 2 的子集。
     * 4. 背包中每一个元素是不可重复放入 -- 01knapsack
     */
    //递推公式：dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);
    //从dp[j]的定义来看，首先dp[0]一定是0
    //如果题目给的价值都是正整数那么非0下标都初始化为0就可以了，如果题目给的价值有负数，那么非0下标就要初始化为负无穷。
    //这样才能让dp数组在递推的过程中取得最大的价值，而不是被初始值覆盖了。
    //本题题目中 只包含正整数的非空数组，所以非0下标的元素初始化为0就可以了。
    /*
     * 确定遍历顺序
     * 如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
     * 
     * 举例推导dp数组
     * dp[j]的数值一定是小于等于j的。
     * 如果dp[j] == j 说明，集合中的子集总和正好可以凑成总和j，理解这一点很重要。
     */

     public boolean canPartition(int[] nums){
        if (nums == null || nums.length == 0){
            return false; 
        }
        int n = nums.length; 
        int sum = 0; 
        for (int num : nums){
            sum += num; 
        }

        if (sum % 2 != 0){
            return false; 
        }

        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < n; i++){
            for (int j = target; j >= nums[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
            if (dp[target] == target){
                return true; 
            }
        }
        return dp[target] == target; 
     }

     public static void main(String[] args){
        int num[] = {1, 5, 11, 5};
        canPartition2(num);
     }

     //2D dp 
     public static boolean canPartition2(int[] nums){
        int len = nums.length;
        int sum = 0; 
        for (int num : nums){
            sum += num;
        }
        if (sum % 2 != 0){
            return false; 
        }

        int target = sum / 2;
        /*
        dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数
          每个数只能用一次，使得这些数的和恰好等于 j。
        */
        boolean[][]dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满  
        //（这里的dp[][]数组的含义就是“恰好”，所以就算容积比它大的也不要）
        if (nums[0] <= target){
            dp[0][nums[0]] = true; 
        }

        // 再填表格后面几行
        //外层遍历物品
        for (int i = 1; i < len; i++){
            //内层遍历背包
            for (int j = 0; j <= target; j++){
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i-1][j]; 

                ////如果某个物品单独的重量恰好就等于背包的重量，那么也是满足dp数组的定义的
                if (nums[i] == j){
                    dp[i][j] = true; 
                    continue; 
                }

                ////如果某个物品的重量小于j，那就可以看该物品是否放入背包
                //dp[i - 1][j]表示该物品不放入背包，如果在 [0, i - 1] 这个子区间内已经有一部分元素，
                //使得它们的和为 j ，那么 dp[i][j] = true；
                //dp[i - 1][j - nums[i]]表示该物品放入背包。如果在 [0, i - 1] 这个子区间内就得找到一部分元素，
                //使得它们的和为 j - nums[i]。
                if (nums[i] < j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
                }
            }
        }
        for (int i = 0; i < len; i++){
            for (int j = 0; j <= target; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[len - 1][target];
     }

     public boolean canPartition3(int[] nums){
        int len = nums.length;
        if (len == 0) return false; 
        int sum = 0; 
        for (int num : nums) sum += num; 
        
        if (sum % 2 == 1) return false; 

        int target = sum / 2;
        int[][] dp = new int[len][target + 1];

        // for(int j = 0; j <= target; j++){
        //     if(j < nums[0])
        //         dp[0][j] = 0;
        //     else
        //         dp[0][j] = nums[0];
        // }

        //initialize dp array 
        for (int j = nums[0]; j <= target; j++)
            dp[0][j] = nums[0];
        

        for (int i = 1; i < len; i++){
            for (int j = 0; j <= target; j++){
                if (j < nums[i])
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i]] + nums[i]);
            }
        }

        //print out DP array
        // for(int x : dp){
        //     System.out.print(x + ",");
        // }
        // System.out.print("    "+i+" row"+"\n");
        return dp[len - 1][target] == target;
     }
}
