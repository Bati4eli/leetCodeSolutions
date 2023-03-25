package leetCode._011_ContainerWithMostWater;

import static java.lang.Math.min;

// https://leetcode.com/problems/container-with-most-water/
//
class Solution {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, area = 0;
        while (i < j) {
            int volume = Math.min(height[i], height[j]) * (j - i);
            area = Math.max(area, volume);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(
                new Solution().maxArea(height)
        );
    }
}