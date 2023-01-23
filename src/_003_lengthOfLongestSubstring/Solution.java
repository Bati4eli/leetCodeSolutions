package _003_lengthOfLongestSubstring;

import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int lengthOfLongestSubstring(String str) {
        System.out.println("\"" + str + "\"");
        if (str.length() <= 1) {
            return str.length();
        }
        int maxSize = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = lengthOfLongestSubstring(str, i);
            maxSize = Math.max(val, maxSize);
        }
        return maxSize;
    }

    public int lengthOfLongestSubstring(String str, int offset) {
        Set<Character> set = new TreeSet<>();
        char[] charArray = str.toCharArray();
        for (int i = offset; i < charArray.length; i++) {
            char symbol = charArray[i];
            if (set.contains(symbol)) {
                return set.size();
            } else {
                set.add(symbol);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().lengthOfLongestSubstring("")
        );
    }
}