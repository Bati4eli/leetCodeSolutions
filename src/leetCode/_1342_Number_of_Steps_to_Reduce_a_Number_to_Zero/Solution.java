package leetCode._1342_Number_of_Steps_to_Reduce_a_Number_to_Zero;

class Solution {
    public static int numberOfSteps(int num) {
        int result = 0;

        while (num > 0) {
            if (num % 2 == 1) {
                --num;
            } else {
                num = num / 2;
            }
            ++result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSteps(10));
    }
}