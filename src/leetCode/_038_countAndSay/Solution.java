package leetCode._038_countAndSay;

class Solution {

    public String countAndSay(int n) {
        if(n==1){
            return "1";
        }
        return countAndSay("1", n-1);
    }

    private String countAndSay(String str, int steps) {

        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        char lastSymb = chars[0];

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (lastSymb == ch) {
                ++counter;
            } else {
                builder.append(counter).append(lastSymb);
                counter = 1;
            }
            lastSymb = ch;
        }
        builder.append(counter).append(lastSymb);

        if (steps <= 1) {
            return builder.toString();
        }
        return countAndSay(builder.toString(), --steps);
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().countAndSay(4)
        );
    }
}