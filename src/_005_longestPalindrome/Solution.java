package _005_longestPalindrome;

class Solution {
    public String longestPalindrome(String str) {
        if (str.length() == 1) {
            return str;
        }
        int middle = str.length() / 2 - str.length() % 2;
        boolean hasOnePalindrome = false;

        for (int i = middle; i >= 0; i--) {
            int index2 = str.length() - i - 1;
            String s1 = str.substring(i, i + 1);
            String s2 = str.substring(index2, index2 + 1);

            if (hasOnePalindrome && !s1.equals(s2)) {
                return str.substring(i + 1, index2);
            } else {
                hasOnePalindrome=true;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.longestPalindrome("ac")
        );
    }
}