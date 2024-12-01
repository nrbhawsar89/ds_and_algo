import java.util.*;

public class Main{
    public static boolean isPalindrome(String s) {
      int len = s.length();
      int i = 0;
       int j = len - 1;
      while (i < j) {
        if (s.charAt(i) != s.charAt(j)) return false;
        i ++;
        j--;
      }
        // Replace this placeholder return statement with your code
        return true;
    }
}
