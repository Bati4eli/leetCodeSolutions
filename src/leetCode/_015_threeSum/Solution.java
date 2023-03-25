package leetCode._015_threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/3sum/description/

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        final int target = 0;
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int g = nums.length - 1;
            while (j < g) {
                int sum = nums[i] + nums[j] + nums[g];
                if (sum == target) {
                    set.add(Arrays.asList(nums[i], nums[j], nums[g]));
                    j++;
                    g--;
                } else if (sum < target) {
                    j++;
                } else {
                    g--;
                }
            }
        }
        output.addAll(set);
        return output;
    }

    public static void main(String[] args) {
//        new ArrayList<>().add(1, 32);
        System.out.println(
                new Solution().threeSum(
                        new int[]{-1, 0, 1, 2, -1, -4}
                )
        );
    }
}