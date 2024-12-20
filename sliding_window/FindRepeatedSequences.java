package sliding_window;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class FindRepeatedSequences {
    public static void main(String args[]) {
            List<String> inputsString = Arrays.asList("ACGT", "AGACCTAGAC", "AAAAACCCCCAAAAACCCCCC",
                "GGGGGGGGGGGGGGGGGGGGGGGGG", "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT", "TTTTTGGGTTTTCCA",
                "AAAAAACCCCCCCAAAAAAAACCCCCCCTG", "ATATATATATATATAT");
        List<Integer> inputsK = Arrays.asList(3, 3, 8, 12, 10, 14, 10, 6);
        for (int i = 0; i < inputsK.size(); i++) {
            System.out.println((i + 1) + ".\tInput sequence: " + inputsString.get(i) + 
                                         "\n\tk: " + inputsK.get(i) + "\n");
            findRepeatedSequences(inputsString.get(i), inputsK.get(i));
            
        }
    }

    public static Set<String> findRepeatedSequences(String dna, int k) {
    if (dna.length() < k) return null;
    /*
     * 1) Assign numerical value to all the unique characters in the string
     * 2) declare a base value
     */
    int baseValue = 4;
    int counter = 1;
    Map<Character, Integer> numValue = new HashMap<>();
    for (int i = 0; i < dna.length(); i ++) {
        char ch = dna.charAt(i);
        if (numValue.containsKey(ch)) continue;
        numValue.put(ch, counter++);
    }
    Set<String> outputSet= new HashSet<>();
    int currentHash = getHash(dna.substring(0, k), baseValue, numValue, k);
    Set<Integer> runningSet = new HashSet<>();
    runningSet.add(currentHash);
    for (int i = 1; i < dna.length() - k; i ++) {
        String subStr = dna.substring(i, i + k);
        char prev = dna.charAt(i- 1);
        char current = dna.charAt(i + k - 1);
        int deduction = (int) Math.pow(baseValue, k - 1)*numValue.get(prev);
        currentHash =  (currentHash - deduction)*4 + numValue.get(current);
        if (runningSet.contains(currentHash)) {
            outputSet.add(dna.substring(i, i + k));
        } else {
            runningSet.add(currentHash);
        }
        
    }
    System.out.println(outputSet);
    return outputSet; 
   }

   private static Integer getHash(String str, int baseValue, Map<Character, Integer> numValue, int k) {
    int sum = 0;
    for (int i = 0; i < str.length(); i ++) {
        sum += Math.pow(baseValue, k - i - 1)*numValue.get(str.charAt(i));
    }
    return sum;
   }
    
}
