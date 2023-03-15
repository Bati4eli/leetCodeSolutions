package leetCode._238_productExceptSelf;

// https://leetcode.com/problems/product-of-array-except-self/

class Solution {

    // working with O(n)

    public static int[] productExceptSelf(int[] nums) {
        int res[] = new int[nums.length];
        int product = 1;

        for (int i = 0; i < nums.length; i++) {
            res[i] = product;
            product *= nums[i];
        }

        product=1;

        for (int i = nums.length - 1; i >= 0; i--) {
            res[i]= res[i] * product;
            product *= nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(
                productExceptSelf(new int[]{1, 2, 3, 4})
        );
    }
}