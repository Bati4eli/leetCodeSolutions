package _383_Ransom_Note;

public class Solution {

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[255];

        for (Character ch : magazine.toCharArray()) {
            ++chars[ch];
        }

        for (Character ch : ransomNote.toCharArray()) {
            if (--chars[ch] < 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

}
