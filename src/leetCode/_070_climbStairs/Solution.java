package leetCode._070_climbStairs;

// https://leetcode.com/problems/climbing-stairs/

class Solution {
    public static int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int prev1 = 1;
        int prev2 = 2;

        for (int i = 2; i < n; i++) {
            int temp = prev2;
            prev2 = prev1 + prev2;
            prev1 = temp;
        }

        return prev2;
    }

    public static void main(String[] args) {
        System.out.println(
                climbStairs(5)
        );
    }
}