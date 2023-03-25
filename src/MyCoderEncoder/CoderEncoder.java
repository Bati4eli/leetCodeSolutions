package MyCoderEncoder;

import leetCode.common.Utils;

import java.util.Arrays;

public class CoderEncoder {

    public static void main(String[] args) {
        final String INPUT_DATA = "MY STRING 1234";
        final String CODE = "MY PASS IS VERY STRONG_";

        Utils.countTime(() -> {
            String data = INPUT_DATA;
            data = encode(data, CODE);
            System.out.println(data);

            data = decode(data, CODE);
            System.out.println(data);
        });
    }

    @FunctionalInterface
    private interface ThreeArgFunc {
        char countIt(char data, char code, char dec);
    }

    public static String encode(String source, String codeString) {
        return universeFunc(source, codeString, (data, code, dec) -> (char) (data - code * dec));
    }

    public static String decode(String source, String codeString) {
        return universeFunc(source, codeString, (data, code, dec) -> (char) (data + code * dec));
    }

    private static String universeFunc(String source, String codeString, ThreeArgFunc func) {
        char[] codes = codeString.toCharArray();
        char[] data = source.toCharArray();

        final char hashData = (char) data.length;
        final char hashCode = getHashCode(codeString);
        char prevDec = (char) (hashData + hashCode);

        for (int i = 0; i < data.length; i++) {
            char code = getCode(codes, prevDec, i); // get code from codes[]
            char dec = getDecorator(hashData, hashCode, i);
            data[i] = func.countIt(data[i], code, dec);
            prevDec -= dec;
        }
        return String.valueOf(data);
    }

    private static char getCode(char[] codes, char prevDec, int iterator) {
        int cursor = getCursor(codes.length, prevDec, iterator);
        //System.out.println(cursor);
        return (char) (codes.length == 0 ? iterator : codes[cursor]);
    }

    private static int getCursor(int length, int prevDec, int iterator) {
        if (length == 0) {
            return 0;
        }
        return length - ((prevDec - iterator) % length) - 1;
    }

    private static char getDecorator(char hashData, char hashCode, int i) {
        int val = hashCode + i * hashData;
        return (char) val;
    }

    private static char getHashCode(String code) {
        return (char) (Math.abs(code.hashCode()) % Character.MAX_VALUE);
    }
}
