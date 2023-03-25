package leetCode._011_ContainerWithMostWater;

// https://leetcode.com/problems/container-with-most-water/
//
class Solution {

    public int maxArea(int[] height) {
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int limit = Math.min(height[i], height[j]);
                int volume = (j - i) * limit;
                result = Math.max(result, volume);
            }
        }
        return result;
    }

    private int countVolume(int[] height, int start, int end) {
        int limit = Math.min(height[start], height[end]);
        return (end - start) * limit;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(
                new Solution().countVolume(height, 1, height.length - 1)
        );
        System.out.println("---------------");
        System.out.println(
                new Solution().maxArea(height)
        );
    }
}