package merge_interval;
import java.util.Arrays;
import java.util.LinkedList;
public class MergeInterval {
    public static int[][] mergeIntervals(int[][] intervals) {
        int L = intervals.length;
        LinkedList<int[]> outputList = new LinkedList<>();
        outputList.add(intervals[0]); //1,5
        for (int i = 1; i < L; i ++) { // i = 3
            int[] currInterval = intervals[i]; // 4,6
            int[] prevInterval = outputList.getLast(); //1,7
            int cSPos = currInterval[0]; //4
            int cEPos = currInterval[1]; //6
            int lastEPos = prevInterval[1]; // 7
            if (cSPos > lastEPos) {
                outputList.add(intervals[i]);
                continue;
            } else if (cEPos <= lastEPos && cSPos <= lastEPos) {
                continue;
            } else if (cSPos <= lastEPos) {
                prevInterval[1] = cEPos; // 1,7
            }
        }
        return outputList.toArray(new int[][]{});
    }
 // Driver code
    public static void main(String[] args) {
        int[][][] allIntervals = {
                {{1, 5}, {3, 7}, {4, 6}},
                {{1, 5}, {4, 6}, {6, 8}, {11, 15}},
                {{3, 7}, {6, 8}, {10, 12}, {11, 15}},
                {{1, 5}},
                {{1, 9}, {3, 8}, {4, 4}},
                {{1, 2}, {3, 4}, {8, 8}},
                {{1, 5}, {1, 3}},
                {{1, 5}, {6, 9}},
                {{0, 0}, {1, 18}, {1, 3}}
        };
        
        int index = 1;
        for (int[][] intervals : allIntervals) {
            System.out.println(index + ".\tIntervals to merge: " + Arrays.deepToString(intervals));
            int[][] result = mergeIntervals(intervals);
            System.out.println("\tMerged intervals: " + Arrays.deepToString(result));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            index++;
        }
    }
    
}
