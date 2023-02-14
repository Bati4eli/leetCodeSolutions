package _017_letterCombinations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
class Solution {

    public static final Map<Character, char[]> BUTTONS = new HashMap<>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.equals("")) {
            return result;
        }
        Deque<String> deque = new ArrayDeque<>();
        deque.add("");

        for (int i = 0; i < digits.length(); i++) {
            char chr = digits.charAt(i);
            char[] bundle = BUTTONS.get(chr);
            int size = deque.size();

            for (int j = 0; j < size; j++) {
                String pull = deque.pollFirst();
                for(char c: bundle){
                    String temp = pull.concat(c+"") ;
                    deque.add(temp);
                }
            }
        }

        while (!deque.isEmpty()) {
            result.add(deque.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().letterCombinations("23")
        );
    }
}