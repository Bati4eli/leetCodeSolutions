package _007_reverseInteger;


//https://leetcode.com/problems/reverse-integer/submissions/884919422/
// Solution from java.lang.Integer:648

class Solution {
    public int reverse(int x) {
        final int radix = 10;
        int result = 0;
        boolean negative = x < 0;
        int limit = negative ? -Integer.MAX_VALUE : Integer.MIN_VALUE;
        int multmin = limit / radix;

        String s = String.valueOf(Math.abs(x));

        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = Character.digit(s.charAt(i), radix);
            if (digit < 0 || result < multmin || result < limit + digit) {
                return 0;
            }
            result *= radix;
            result -= digit;
        }
        return negative ? result : -result;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().reverse(-123)
        );
    }
}