package _013_roman_to_integer;

class Solution {
    public static int romanToInt(String roman) {
        int result = 0;
        int preResult = getInt(roman.charAt(0));
        char prevCh = roman.charAt(0);

        for (int i = 1; i < roman.length(); i++) {
            char currentCh = roman.charAt(i);
            int currentInt = getInt(currentCh);
            int prevInt = getInt(prevCh);

            if (prevInt == currentInt) {
                preResult += currentInt;
            }  else if (prevInt > currentInt) {
                result += preResult;
                preResult = currentInt;
            } else {
                result += currentInt - preResult;
                preResult = 0;
            }
            prevCh = currentCh;
        }
        result += preResult;

        return result;
    }

    private static int getInt(Character ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));         // 3
        System.out.println(romanToInt("IV"));          // 4
        System.out.println(romanToInt("LVIII"));       // 58
        System.out.println(romanToInt("MCMXCIV"));     // 1994
    }

}