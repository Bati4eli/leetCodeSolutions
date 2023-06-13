package leetCode._153_findMin;

class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int medium = (left + right) / 2;
            if (nums[medium] < nums[right]) {
                right = medium;
            } else {
                left = medium + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        var r = new Solution().findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println(r);
    }
}