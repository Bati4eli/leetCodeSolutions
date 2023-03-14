package leetCode._121_maxProfit;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int minimal = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < minimal) {
                minimal = price;
            } else {
                int prf = price - minimal;
                if (prf > profit) {
                    profit = prf;
                }
            }
        }

        return profit;
    }

}