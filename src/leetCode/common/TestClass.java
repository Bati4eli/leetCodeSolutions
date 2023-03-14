package leetCode.common;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestClass {

    public static void main(String[] args) {
        AtomicBoolean ab = new AtomicBoolean();
        ab.get();
    }
//    public static void main(String[] args) {
//        Map<Value, String> map = new WeakHashMap<>();
//        Value key = new Value();
//        key.data = "102";
//        map.put(key,"sdfsf");
//
//
//        System.out.println(map.get(key));
//        key.data = "8990";
//        System.out.println(map.get(key));
//        key.data = "102";
//        System.out.println(map.get(key));
//
//        map.forEach((value, s) -> System.out.println(value +":"+ s));
//    }
//
//
//    static class Value {
//        String data;
//
//        @Override
//        public boolean equals(Object o) {
//            return data.equals(o);
//        }
//
//        @Override
//        public int hashCode() {
//            return data.hashCode();
//        }
//    }

//    public static void main(String[] args) {
//       String s1= "Хабрахабр -- торт! ИГУБЧПЮ";
//       String s2= "Хабрахабр -- торт! МЭУХЦЙГ";
//        System.out.println(s1.hashCode()==s2.hashCode());
//
//       Set<String> set = new HashSet<>();
//       set.add(s1);
//       set.add(s2);
//       for(String s: set){
//           System.out.println(s);
//       }
//    }
}
