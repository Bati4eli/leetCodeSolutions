package leetCode._043_MultiplyStrings;

public class Solution {

    public String multiply(String num1, String num2) {
        if (num1.length() > num2.length()) {
            String buff = num2;
            num2 = num1;
            num1 = buff;
        }

        for (int i = (num1.length() - 1); i >= 0; i--) {
            int degree = (num1.length() - i);
            int r = quad(degree);
            System.out.println("i: " + i + " degree= " + degree + " r = " + r);

        }
        return "";
    }

    private int quad(int n) {
        int r = 1;
        for (int i = 1; i < n; i++) {
            r *= 10;
        }
        return r;
    }

    private Character getChar(String str, int index) {
        try {
            return str.charAt(index);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().multiply("122", "340")
        );
    }
}
