package two_heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections;
public class SlidingWindowMedian {
    
    public static double[] medianSlidingWindow(int[] nums, int k) { //{3, 1, 2, -1, 0, 5, 8} k = 4
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < k; i ++) { // 3
            insert(nums[i], maxHeap, minHeap); // -1
        }
        //// [0, -1], [2, 5]
        double median = getMedian(maxHeap, minHeap); // .5
        result[0] = median; // [1.5, .5]
        for (int i = k; i < nums.length; i ++) { // 5
            // [0, -1], [2, 5]
            int currNum = nums[i];   // 8
            int outgoingNum = nums[i - k]; // 2
            
            if (minHeap.size() > 0 && outgoingNum >= minHeap.peek()) { 
                minHeap.remove(outgoingNum); // [0, -1], [ 5]
            } else if (maxHeap.size() > 0 ){
                maxHeap.remove(outgoingNum);
            }
            insert(currNum, maxHeap, minHeap); // 8
            median = getMedian(maxHeap, minHeap); // .5
            result[i - k + 1] = median; // [1.5, .5]
            // System.out.println(maxHeap.size());
            // System.out.println(minHeap.size());
        }
        
        return result;
     }

     static double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) { // [0, -1], [2, 5]
        if (maxHeap.size() == minHeap.size()) {
            return ((double)maxHeap.peek() + (double)minHeap.peek())/2;
        } 
        return (double)maxHeap.peek() ;
     }

     public static void insert(int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) { //8,  [], []
        if (maxHeap.size() == 0 || maxHeap.peek() >= num) { 
            maxHeap.offer(num); // [ 0, -1], [5]
        } else {
            minHeap.offer(num);// [ 0, -1], [5, 8]
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());// [0, -1], [0, 2]
        } else if (minHeap.size() >= maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
     }

     public static void main(String[] args) {
        int [][]arr = {{3, 1, 2, -1, 0, 5, 8}, {1, 2}, {4, 7, 2, 21}, {22, 23, 24, 56, 76, 43, 121 ,1 ,2 ,0 ,0 ,2 ,3 ,5}, {1, 1, 1, 1, 1}};
        int[] k = {4, 1, 2, 5, 2};
        for(int i=0; i<k.length; i++){
            System.out.print(i+1);
            System.out.println(".\tInput array = " + Arrays.toString(arr[i]) + ", k = " + k[i]);
            double[] output = medianSlidingWindow(arr[i], k[i]);
            System.out.println("\tMedians = " + Arrays.toString(output));
            
        }
    }
}
