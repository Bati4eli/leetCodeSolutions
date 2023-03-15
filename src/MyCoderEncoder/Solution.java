package MyCoderEncoder;

import leetCode.common.Utils;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        //'\uFFFF' 65535
        final String code = System.currentTimeMillis() +"";
        final String INPUT_DATA = "Utils.countTime(() -> {\n" +
                "            String data = INPUT_DATA;\n" +
                "            data = encode(data, code);\n" +
                "            System.out.println(data);\n" +
                "\n" +
                "            data = decode(data, code);\n" +
                "            System.out.println(data);\n" +
                "        });";

        Utils.countTime(() -> {
            String data = INPUT_DATA;
            data = encode(data, code);
            System.out.println(data);

            data = decode(data, code);
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

        final char hash1 = (char) data.length;
        final char hash2 = getHashCode(codes);
        char prevDec = (char) (hash1 + hash2);

        for (int i = 0; i < data.length; i++) {
            char code = getCode(codes, prevDec, i); // get code from codes[]
            char dec = getDecorator(hash1, hash2, i);
//           System.out.println((int) dec);
            data[i] = func.countIt(data[i], code, dec);
            prevDec = dec;
        }
        return String.valueOf(data);
    }

    private static char getCode(char[] codes, char prevDec, int i) {
        int cursor = getCursor(codes.length, prevDec);
        return (char) (codes.length == 0 ? i : codes[cursor]);
    }

    private static int getCursor(int length, int prevDec) {
        if (length == 0) {
            return 0;
        }
        return length - (prevDec % length) - 1;
    }

    private static char getDecorator(char hashData, char hashCode, int i) {
        int val = hashCode + i * hashData ;
        return (char) val;
    }

    private static char getHashCode(char[] arr) {
        return (char) (Math.abs(Arrays.hashCode(arr)) % Character.MAX_VALUE);
    }
}
