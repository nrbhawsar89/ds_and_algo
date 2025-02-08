package TopK;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeString {
    public static String reorganizeString(String string1) { // abb

        Map<Character, Integer> strMap = new HashMap<>();
        for (Character ch : string1.toCharArray()) {
          strMap.compute(ch, (k, v) -> v == null? 1 : v + 1); 
        }
        // strMap = b:2 a:1
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(strMap.entrySet());
        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;
        while (pq.size() > 0) {
          Map.Entry<Character, Integer> current = pq.poll(); // b:1
          if (prev != null) {
            pq.offer(prev); // b:1
          }
          sb.append(current.getKey()); //bab
          current.setValue(current.getValue() - 1); // a:0
          if (current.getValue() != 0) {
            prev = current; // b
          } else {
            prev = null;
          }
        }
    
        return sb.toString();
    }
    public static void main(String args[]) {
        String[] inputs = {
            "programming",
            "hello",
            "fofjjb",
            "abbacdde",
            "aba",
            "awesome"
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string: \"" + inputs[i] + "\"");
            System.out.println("\tCharacter counts: " + reorganizeString(inputs[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

    }
}
