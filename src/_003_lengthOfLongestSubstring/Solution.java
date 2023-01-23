package _003_lengthOfLongestSubstring;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String str) {
        if (str.length() <= 1) {
            return str.length();
        }
        int maxSize = 0;
        Set<Character> set = new HashSet<>();
        //-------------
        for (int j = 0; j < str.length(); j++) {
            char symbol = str.charAt(j);
            set.add(symbol);
        }
        if (set.size() <= 2) {
            return set.size();
        }
        set = new HashSet<>();
        //-------------
        for (int offset = 0; offset < str.length(); offset++) {
            // int val = lengthOfLongestSubstring(str, offset);
            int val = 0;

            for (int j = offset; j < str.length(); j++) {
                char symbol = str.charAt(j);
                if (set.contains(symbol)) {
                    break;
                } else {
                    set.add(symbol);
                }
            }

            val = set.size();
            maxSize = Math.max(val, maxSize);
            set = new HashSet<>();
        }
        return maxSize;
    }


    public static void main(String[] args) {
        System.out.println(
                new Solution().lengthOfLongestSubstring("pwwkew")
        );
    }
}