import java.util.*;
import java.util.stream.IntStream;
public class SumOfThree{
   public static boolean findSumOfThree(int[] nums, int target) {
    Arrays.sort(nums);
    // -1,0,1
    boolean foundSum = IntStream.range(0, nums.length).anyMatch(idx -> {
      int element = nums[idx];
      int start = idx + 1;
      int end = nums.length -1;
      int nTarget = target - element;
      boolean match = false;
      while (start < end) {
        if (nums[start] + nums[end] == nTarget) {
          match = true;
          break;
        }
        else if (nums[start] + nums[end] > nTarget) end --;
        else start ++;
      }
      return match;
    });
    return foundSum;
    
   }
}
