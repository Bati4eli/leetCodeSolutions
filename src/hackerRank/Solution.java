package hackerRank;

import leetCode.common.Utils;

import java.util.Arrays;

public class Solution {

    @FunctionalInterface
    private interface ThreeArgFunc {
        public char doit(char a, char b, char c);
    }

    private static String universeFunc(String source, String codeString, ThreeArgFunc func) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();

        final char hash1 = (char) data.length;
        final char hash2 = getHashCode(codes);
        char prevDec = (char) (hash1 + hash2);

        for (int i = 0; i < data.length; i++) {
            int cursor = getCursor(codes.length, prevDec);
            char dec = getDecorator(hash1, hash2, i);
//           System.out.println((int) dec);
            data[i] = func.doit(data[i] , codes[cursor] , dec);
            prevDec = dec;
        }

        return String.valueOf(data);
    }

    public static String encode(String source, String codeString) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();

        final char hash1 = (char) data.length;
        final char hash2 = getHashCode(codes);
        char prevDec = (char) (hash1 + hash2);

        for (int i = 0; i < data.length; i++) {
            int cursor = getCursor(codes.length, prevDec);
            char dec = getDecorator(hash1, hash2, i);
//           System.out.println((int) dec);
            data[i] = (char) (data[i] - codes[cursor] - dec);
            prevDec = dec;
        }

        return String.valueOf(data);
    }

    public static String decode(String source, String codeString) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();
        final char hashData = (char) data.length;
        final char hashCode = getHashCode(codes);
        char prevDec = (char) (hashData + hashCode);

        for (int i = 0; i < data.length; i++) {
            int cursor = getCursor(codes.length, prevDec);
            char dec = getDecorator(hashData, hashCode, i);
            //          System.out.println((int) dec);
            data[i] = (char) (data[i] + codes[cursor] + dec);
            prevDec = dec;
        }

        return String.valueOf(data);
    }

    private static int getCursor(int length, int prevDec) {
        return length - (prevDec % length) - 1;
    }

    private static char getDecorator(char hashData, char hashCode, int i) {
        int val = hashCode - i * hashData;
        return (char) val;
    }

    private static char getHashCode(char[] arr) {
        return (char) (Math.abs(Arrays.hashCode(arr)) % Character.MAX_VALUE);
    }

    public static void main(String[] args) {
        //'\uFFFF' 65535
        Utils.countTime(() -> {
            String res = "1234567890000000000aaaaaaabcdef";
            System.out.println("encode: =======================");
            res = encode(res, " ");
            System.out.println(res);

            System.out.println("decode: =======================");
            res = decode(res, " ");
            System.out.println(res);
        });
    }
}
