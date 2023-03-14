package hackerRank;

import leetCode.common.Utils;

public class Solution {

    public static String encode(String source, String codeString) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();
        char prevDec = (char) (codes.length + data.length);

        for (int i = 0; i < data.length; i++) {
            int cursor = getCursor(codes, prevDec);
            char dec = getDecorator(i, codes.length, data.length);

            data[i] = (char) (data[i] + codes[cursor] + dec);
            prevDec =(char) (prevDec + dec) ;
        }

        return String.valueOf(data);
    }

    public static String decode(String source, String codeString) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();
        char prevDec = (char) (codes.length + data.length);

        for (int i = 0; i < data.length; i++) {
            int cursor = getCursor(codes, prevDec);
            char dec = getDecorator(i, codes.length, data.length);

            data[i] = (char) (data[i] - codes[cursor] - dec);
            prevDec =(char) (prevDec + dec) ;
        }

        return String.valueOf(data);
    }


    private static int getCursor(char[] codes, char prevDec) {
        return codes.length - (prevDec % codes.length) - 1;
    }

    private static char getDecorator(int i, int length, int length2) {
        char c = (char) ((length + length2) - i);
        if (c % 2 == 0) {
            return (char) -c;
        }
        return c;
    }


    public static void main(String[] args) {
        Utils.countTime(() -> {
            System.out.println(
                    encode("0000000000", "0000")
            );
            String res = encode("0000000000", "000");
            System.out.println(res);
            res = decode(res, "000");
            System.out.println(res);
        });

    }
}
