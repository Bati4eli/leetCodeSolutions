package leetCode._008_String_to_Integer;

class Solution {

    public int myAtoi(String string) {
        final int radix = 10;
        boolean numberStarted = false;
        boolean negative = false;
        boolean negFound = false;
        boolean zeroBeforeNumber = false;
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            //int digit = Character.digit(string.charAt(i), radix);
            char ch = string.charAt(i);
            if ((ch == '-' || ch == '+') && !numberStarted && !negFound) {
                negative = ch == '-';
                negFound = true;
                if (zeroBeforeNumber) {
                    return 0;
                }
            } else if (ch == '.' && numberStarted) {
                return getAnInt(negative, result);
            } else if (ch == ' ' || (ch == '0' && !numberStarted)) {
                if (ch == '0') {
                    zeroBeforeNumber = true;
                }
                continue;
            } else if (ch >= '0' && ch <= '9') {
                if (!numberStarted ) {
                    numberStarted = true;
                }
                int digit = Character.digit(ch, radix);
                int limit = - getLimit(negative);
                int multmin = limit / radix;
                if (digit < 0 || result < multmin ) {
                    return -limit;
                }
                result *= radix;
                if(result < limit + digit){
                    return 0;
                }
                result -= digit;
            } else {
                break;
            }
        }
        return getAnInt(negative, result);
    }

    private int getAnInt(boolean negative, int result) {
        return negative ? result : -result;
    }

    private int getLimit(boolean negative) {
        return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().myAtoi("  -0012a42")
        );
    }
}
