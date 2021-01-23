package it.test.simple;

import java.util.Arrays;

/**
 * 凑钱包
 */
public class TestDp {
    public static void main(String[] args) {
        TestDp a = new TestDp();
        final int i = a.coinChangey(new int[]{1, 2, 5}, 11);
        System.out.println(i);

    }

    public int coinChangex(int[] coins, int amount) {
        //1. 确定终止条件
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangex(coins, amount - coin);
            if (res>=0)
                min = Math.min(min, res + 1);
        }
        return min;
    }

    public int coinChangey(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangey(coins, amount - coin, new int[amount]);
            if (res>=0) {
                min = Math.min(min, res + 1);
            }
        }
        return min;
    }

    public int coinChangey(int[] coins, int amount, int[] dp) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int min = Integer.MAX_VALUE;
        if (dp[amount]!=0) return dp[amount];
        for (int coin : coins) {
            int res = coinChangey(coins, amount - coin);
            if (res>=0) {
                min = Math.min(min, res + 1);
                dp[amount] = min;
            }
        }
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        if (coins.length == 0){
            return -1;
        }
        for (int i = 0; i<=amount; i++){
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }

}
