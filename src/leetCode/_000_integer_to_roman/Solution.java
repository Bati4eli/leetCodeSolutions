package leetCode._000_integer_to_roman;

class Solution {

    public static String intToRoman(int num) {
        int length = String.valueOf(num).length();
        int[] digits = getDigits(num, new int[length], length - 1, 0);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int numDec = length - i - 1;
            int factor = factor(numDec);
            int currDigit = digits[i];
            if (currDigit == 1 || currDigit == 5) {
                char roman = getRoman(currDigit * factor);
                builder.append(roman);
            } else if (currDigit >= 4 && currDigit <= 8) {
                int diff = Math.abs(currDigit - 5);
                char addChar = getRoman(factor);
                int currFiveFactor = 5 * factor;
                if (currDigit < 5) {
                    repeat(addChar, diff,builder).append(getRoman(currFiveFactor));
                } else {
                    builder.append(getRoman(currFiveFactor));
                    repeat(addChar, diff,builder);
                }
            } else if (currDigit != 0) {
                int diff = currDigit < 5 ? currDigit : 1;
                char addChar = getRoman(factor);
                int currTenFactor = 10 * factor;
                if (currDigit == 9) {
                    repeat(addChar, diff,builder).append(getRoman(currTenFactor));
                } else {
                    repeat(addChar, diff,builder);
                }
            }
        }

        return builder.toString();
    }

    private static StringBuilder repeat(char chr, int amount, StringBuilder stringBuilder) {
        int i = 0;
        while (i++ < amount) {
            stringBuilder.append(chr);
        }
        return stringBuilder;
    }

    private static int factor(int powValue) {
        return powValue == 0 ? 1 : pow(10, powValue);
    }

    private static int pow(int value, int powValue) {
        int result = value;
        for (int i = 1; i < powValue; i++) {
            result *= value;
        }
        return result;
    }

    private static int[] getDigits(int num, int[] result, int lenght, int index) {
        int numDev = num / 10;
        int digit = num % 10;
        result[lenght - index] = digit;
        if (numDev > 0) {
            getDigits(numDev, result, lenght, ++index);
        }
        return result;
    }

    private static char getRoman(int ch) {
        switch (ch) {
            case 1:
                return 'I';
            case 5:
                return 'V';
            case 10:
                return 'X';
            case 50:
                return 'L';
            case 100:
                return 'C';
            case 500:
                return 'D';
            case 1000:
                return 'M';
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));        // III
        System.out.println(intToRoman(4));        // IV
        System.out.println(intToRoman(6));        // VI
        System.out.println(intToRoman(58));       // LVIII
        System.out.println(intToRoman(1994));     // MCMXCIV
    }

}