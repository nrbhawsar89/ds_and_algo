package two_heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
public class MaximizeCapital {

    public static int maximumCapital(int c, int k, int[] capitals, int[] profits) { 
		//1 , 3 , [0,1,2] , [1,2,3]
        PriorityQueue<int[]> capitalPQ = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
		for (int i =0; i < capitals.length; i ++) {
			capitalPQ.offer(new int[]{capitals[i], profits[i]});
		} // [0,1]. [1,2], [2,3]
		PriorityQueue<int[]> profitPQ = new PriorityQueue<>((a,b) -> Integer.compare(b[0], a[0]));
		while (k > 0) { // 3
			
			while (capitalPQ.size() > 0 && c >= capitalPQ.peek()[0]) { // 1 >= 2
				int capital[] = capitalPQ.poll(); // [1,2]
				profitPQ.offer(new int[]{capital[1], capital[0]}); // [2,1], [1,0] 
			}
			if (profitPQ.size() == 0) break;
			int[] chosenProfit = profitPQ.poll(); // [2,1]
			c += chosenProfit[0]; // 3
			k --; // 2
		}
        return c;
    }
    public static void main(String[] args) {
		int[] c = { 1, 2, 1, 7, 2 };
		int[] k = { 2, 3, 3, 2, 4 };
		int[][] capitals = {
			{1, 2, 2, 3}, 
      		{1, 3, 4, 5, 6}, 
      		{1, 2, 3, 4}, 
      		{6, 7, 8, 10}, 
      		{2, 3, 5, 6, 8, 12}
		};
		int[][] profits = {
			{2, 4, 6, 8}, 
        	{1, 2, 3, 4, 5}, 
        	{1, 3, 5, 7}, 
        	{4, 8, 12, 14}, 
        	{1, 2, 5, 6, 8, 9}
		};
		for (int i = 0; i<k.length; i++) {
			System.out.println((i + 1) + ".\tGiven capitals: " + Arrays.toString(capitals[i]));
			System.out.println("  \tAdding capital values...");
			System.out.println(maximumCapital(c[i], k[i], capitals[i], profits[i]));
			//System.out.println(PrintHyphens.repeat("-", 100));
		}

	}
    
}
