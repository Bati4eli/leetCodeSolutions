package leetCode._001_TwoSum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            Integer second = target - first;

            Integer secondIndex = map.get(second);
            if (secondIndex != null && i != secondIndex) {
                return new int[]{i, secondIndex};
            }
        }

        return new int[0];
    }

    public static void main(String[] args) {
        Solution ss = new Solution();
        int[] res = ss.twoSum(new int[]{3,2,4}, 6);
        System.out.println(res);
    }
}