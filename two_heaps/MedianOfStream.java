package two_heaps;

import java.util.Collections;
import java.util.PriorityQueue;
public class MedianOfStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    public MedianOfStream() {
        minHeap = new PriorityQueue<>();
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      }
    
      public void insertNum(int num) {
        
        if (minHeap.size() == 0) {
            minHeap.offer(num);
            return;
        }
        if (num > minHeap.peek()) {
            minHeap.offer(num);
            balance();
            return;
        } else {
            maxHeap.offer(num);
            balance();
            return;
        }
      }

      private void balance() {
        if (minHeap.size() > maxHeap.size() + 1) {
            int popped = minHeap.poll();
            maxHeap.offer(popped);
        } else if (maxHeap.size() > minHeap.size() + 1) {
            int popped = maxHeap.poll();
            minHeap.offer(popped);
        }
      }
    
      public double findMedian() {
          // Replace this placeholder return statement with your code
          if (minHeap.size() > maxHeap.size()) return minHeap.peek();
          else if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
            return ((double) minHeap.peek() + (double) maxHeap.peek())/2;
      }
      public static void main(String[] args) {
        // Driver code
        int[] nums = {35, 22, 30, 25, 1};
        MedianOfStream medianOfAges = null;
        for(int i=0; i< nums.length; i++){
          System.out.print(i+1);
          System.out.print(".\tData stream: [");
          medianOfAges = new MedianOfStream();
          for(int j=0; j<=i; j++){
            System.out.print(nums[j]);
            if(j != i)
                System.out.print(", ");
            medianOfAges.insertNum(nums[j]);
          }
          System.out.println("]");
          System.out.println("\tThe median for the given numbers is: " + medianOfAges.findMedian());
        }
        
      }
    
}
