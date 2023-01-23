package _003_lengthOfLongestSubstring;

class Solution {
    public int lengthOfLongestSubstring(String str) {
        if (str.length() <= 1) {
            return str.length();
        }
        int maxSize = 0;
        MySet set = new MySet();
        //-------------
        for (int j = 0; j < str.length(); j++) {
            char symbol = str.charAt(j);
            set.add(symbol);
        }
        if (set.size() <= 2) {
            return set.size();
        }
        set.clear();
        //-------------
        for (int offset = 0; offset < str.length(); offset++) {

            for (int j = offset; j < str.length(); j++) {
                char symbol = str.charAt(j);
                if (set.contains(symbol)) {
                    break;
                } else {
                    set.add(symbol);
                }
            }
            maxSize = Math.max(set.size(), maxSize);
            set.clear();
        }
        return maxSize;
    }

    static class MySet {
        boolean[] array = new boolean[127];
        int size = 0;

        public int size() {
            return size;
        }

        public boolean contains(char o) {
            return array[o];
        }

        public boolean add(char character) {
            boolean cont = array[character];
            if (!cont) {
                ++size;
            }
            return array[character] = true;
        }

        public void clear() {
            size = 0;
            array = new boolean[256];
        }
    }


    public static void main(String[] args) {
        System.out.println(
                new Solution().lengthOfLongestSubstring("pwwkew")
        );

        for (int i = 0; i < 256; i++) {
            System.out.println("# " + i + " : " + Character.toString(i));
        }
    }
}