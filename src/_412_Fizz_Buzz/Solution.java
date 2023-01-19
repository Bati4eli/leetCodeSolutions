package _412_Fizz_Buzz;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static String FIZZ = "Fizz";
    public static String BUZZ = "Buzz";
    public static String FIZZBUZZ = "FizzBuzz";

    public List<String> fizzBuzz(int n) {
        List<String> list = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            boolean isFizz = i % 3 == 0;
            boolean isBuzz = i % 5 == 0;

            if (!isFizz && !isBuzz) {
                list.add(i + "");
            } else if (isFizz && !isBuzz) {
                list.add(FIZZ);
            } else if (!isFizz && isBuzz) {
                list.add(BUZZ);
            } else {
                list.add(FIZZBUZZ);
            }
        }

        return list;
    }

}
