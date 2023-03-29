package leetCode._322_coinChange;

// https://leetcode.com/problems/coin-change/

import java.util.Arrays;

class Solution {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);

        return tryToSolve(coins, amount, coins.length - 1);
    }

    private static int tryToSolve(int[] coins, int amount, int coinNum) {
        int left = amount;
        int count = 0;
        int cur = coinNum;

        while (cur >= 0 && left > 0) {
            int i = left / coins[cur];

            if (i > 0) {
                count += i;
                left = left % coins[cur];
            }
            cur--;
        }

        if (left > 0 && coinNum == 0) {
            return -1;
        } else if (coinNum > 0) {
           return tryToSolve(coins, amount, --coinNum);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                coinChange(new int[]{186,419,83,408}, 6249)
        );
    }
}