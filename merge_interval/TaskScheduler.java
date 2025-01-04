package merge_interval;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
public class TaskScheduler {
    public static int leastTime(char[] tasks, int n) {
        int maxCount = 0;
        char maxChar = tasks[0];
        Map<Character, Integer> charCount = new HashMap<>();
        for (Character task : tasks) {
            charCount.compute(task, (key, count) -> 
                (count == null) ? 1 : count + 1
            );
            if (maxCount < charCount.get(task)) {
              maxCount = charCount.get(task);
              maxChar = task;
            }
        }
        int idleTime = (maxCount - 1)*n;
        charCount.remove(maxChar);
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
          if (entry.getValue() == maxCount) {
            idleTime -= maxCount - 1;
          } else {
            idleTime -= entry.getValue();
          }
        }
        idleTime = Math.max(idleTime, 0);
        return idleTime + tasks.length;

    }
    public static void main(String[] args) {
        char[][] allTasks = {
          
{'A','B','C','O','Q','C','Z','O','X','C','W','Q','Z','B','M','N','R','L','C','J'} ,
          {'A', 'A', 'A', 'B', 'B', 'C', 'C'},
          {'S', 'I', 'V', 'U', 'W', 'D', 'U', 'X'},
          {'M', 'A', 'B', 'M', 'A', 'A', 'Y', 'B', 'M'},
          {'A', 'K', 'X', 'M', 'W', 'D', 'X', 'B', 'D', 'C', 'O', 'Z', 'D', 'E', 'Q'}};

        int[] allNs = {10, 1, 0, 3, 3};

        for (int i = 0; i < allTasks.length; i++) {
          System.out.print((i + 1) + ".\tTasks: ");
          char[] tasks = allTasks[i];
          for(int j = 0; j < tasks.length; j++) {
            System.out.print(tasks[j]);
            if (j != tasks.length - 1) {
                System.out.print(", ");
            }
          }
          System.out.println("\n\tn: " + allNs[i]);

          int minTime = leastTime(allTasks[i], allNs[i]);
          System.out.println("\tMinimum time required to execute the tasks: " + minTime);
          System.out.println('-' + String.join("", Collections.nCopies(100, "-")) + '\n');
        }
    }
}