package merge_interval;
import java.util.List;
import java.util.ArrayList;

public class MeetingRomms2 {
    public static int findSets(int[][] intervals) {
        List<List<int[]>> meetingIntervals = new ArrayList<>();
       int L = intervals.length;
       for (int i = 0; i < L; i ++) {
        boolean overlapped = true;
        int[] interval = intervals[i];
        for (List<int[]> mInterval : meetingIntervals) {
            for (int[] inter : mInterval) {
                if (!isOverlap(inter, interval)) {
                    mInterval.add(interval);
                    overlapped = false;
                    break;
                }
            }
            if (!overlapped) break;
        }
        if (overlapped) {
            List<int[]> tmpArra = new ArrayList<int[]>(List.of(interval));
            meetingIntervals.add(tmpArra);
        }
       }
        // Replace this placeholder return statement with your code
        return meetingIntervals.size();
    }

    private static boolean isOverlap(int[] arr1, int[] arr2) {
        if (arr2[0] < arr1[1]) return true;
        return false;
    }

    public static void main(String args[]) {
        int[][] arr ={{1,7},{2,6},{3,7},{4,8},{5,8}};
        System.out.println(findSets(arr));
    }
}
