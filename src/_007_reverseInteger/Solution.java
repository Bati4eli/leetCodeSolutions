package _007_reverseInteger;

class Solution {
    public int reverse(int x) {
        StringBuilder str = new StringBuilder();
        boolean minus = x < 0;
        String s = String.valueOf(Math.abs(x));
        if(minus)
            str.append("-");
        for (int i = s.length()-1; i >= 0; i--) {
            str.append(s.charAt(i));
        }
        try{
           return Integer.parseInt(str.toString()) ;
        } catch (Exception e){
           return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().reverse(1534236469)
        );
    }
}