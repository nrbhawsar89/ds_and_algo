package two_heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
public class MinimumWage {
    public static double minCostToHireWorkers(int[] quality, int[] wage, int k) {
        int N = wage.length;
        PriorityQueue<Double[]> minHeap = new PriorityQueue<>( (a,b) -> (int) (a[0] - b[0]) );
        for (int i = 0; i < N; i ++) {
            double rat = (double) wage[i]/quality[i];
            minHeap.offer(new Double[]{rat,(double) i});
        }
        double maxRatio = 0;
        int qualitySum = 0;
        while (k > 0) {
            Double[] rat = minHeap.poll();
            maxRatio = Math.max(maxRatio, rat[0]);
            qualitySum += quality[rat[1].intValue()];
            k --;
        }
        return (int)qualitySum*maxRatio;
    }
    public static void main(String[] args) {
        int[][] qualities = {
            {7, 8, 6, 10},
            {3, 1, 10, 10, 1},
            {4, 5, 6},
            {2, 3, 1},
            {10, 10, 10}
        };

        int[][] wages = {
            {70,90,80,100},
            {4, 8, 2, 2, 7},
            {8, 10, 12},
            {5, 6, 2},
            {50, 60, 70}
        };
        int[] k_values = {2, 3, 2, 2, 2};
        for (int i = 0; i < qualities.length; i++) {
            System.out.println((i + 1) + ".\tqualities: " + Arrays.toString(qualities[i]));
            System.out.println("\twages: " + Arrays.toString(wages[i]));
            System.out.println("\tk: " + k_values[i]);
            double result = MinimumWage.minCostToHireWorkers(qualities[i], wages[i], k_values[i]);
            System.out.println("\n\tMinimum cost to hire " + k_values[i] + " workers = " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
