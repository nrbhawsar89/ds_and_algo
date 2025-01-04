package merge_interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class EmployeeFreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        EmployeeFreeTime eft = new EmployeeFreeTime();
       List<Interval> allIntervals = new ArrayList<>();
       for (List<Interval> sh : schedule) {
            for (Interval in : sh) {
                allIntervals.add(in);
            }
       }
       Collections.sort(allIntervals, (a,b) -> Integer.compare(a.start, b.start));
       LinkedList<Interval> merged = new LinkedList<>();
       merged.add(allIntervals.get(0));
       for (int i = 1; i < allIntervals.size(); i ++) {
        Interval currInterval = allIntervals.get(i);
        Interval prevInterval = merged.getLast();
        if (currInterval.start > prevInterval.end) {
            merged.add(currInterval);
            continue;
        }
        prevInterval.end = Math.max(prevInterval.end, currInterval.end);
       }
       if (merged.size() == 1) return null;
       List<Interval> ans = new ArrayList<Interval>();
       for (int i = 1; i < merged.size(); i ++) {
        Interval prev = merged.get(i-1);
        Interval curr = merged.get(i);
        Interval nInterval = eft.new Interval(prev.end, curr.start);
        ans.add(nInterval);
       }
        
        return ans;
    }


    class Interval{
        int start;
        int end;
        boolean closed;
        public Interval(int start, int end)
        {
            this.start = start;
            this.end = end;
            this.closed = true; // by default, the interval is closed
        }
            
        // set the flag for closed/open
        public void setClosed(boolean closed)
        {
            this.closed = closed;
        }
     
    }
}
