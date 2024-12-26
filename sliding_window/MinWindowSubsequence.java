package sliding_window;

public class MinWindowSubsequence {
    public static void main(String args[]) {
        String[] str1 = {"abcdebdde", "fgrqsqsnodwmxzkzxwqegkndaa", "zxcvnhss", "alpha", "beta"};
        String[] str2 = {"bde", "kzed", "css", "la", "ab"};
        
        for (int i = 0; i < str1.length; i++) {
          String result = minWindow(str1[i], str2[i]);
          System.out.println(result);
        //   System.out.println((i + 1) + ".\t Input String: " + "(" + str1[i] + ", " + str2[i] + ")");
        //   System.out.println("\t Length ofstr1 is: " + result[0]);
        //   System.out.println("\t Length ofstr2 is: " + result[1]);
        //   System.out.println(new String(new char[100]).replace('\0', '-'));
        
    }
}
    public static String minWindow(String str1, String str2) {
        int L1 = str1.length();
        int L2 = str2.length();
        int pArr[] = new int[L2];
        int pos1 = 0; int pos2 = 0;
        int minLength = L1;
        String output = null;
        for (int i = 0; i < L1; i++ ) {
            pos1 = i;
            pos2 = 0;
            while (pos2 < L2 && pos1 < L1) {
                if (str1.charAt(pos1) == str2.charAt(pos2)) {
                    pArr[pos2] = pos1;
                    pos2++;
                }
                pos1++;
            }
            if (pos2 != L2) break;
            pos2--;
            pos1--;
            while (pos2>=i && pos1 >= 0) {
                if (str1.charAt(pos1) == str2.charAt(pos2)) {
                    pArr[pos2] = pos1;
                    pos2--;
                }
                pos1--;
            }
            if (pArr[L2-1] - pArr[0] + 1 < minLength) {
                minLength = pArr[L2-1] - pArr[0] + 1;
                output = str1.substring(pArr[0], pArr[L2 - 1] + 1);
            }
            i = pArr[L2 - 1];
        }
        return output;

    }
    
}
