package two_heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
public class MinimumMachines {
    public static int tasks(List<List<Integer>> tasksList) { // [[1,1],[5,5],[8,8],[4,4],[6,6],[10,10],[7,7]]

    PriorityQueue<List<Integer>> tasksPQ = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
    PriorityQueue<List<Integer>> machinesPQ = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));
    for (List<Integer> task : tasksList) {
        tasksPQ.offer(task);
    } // [[1,1],[4,4],[5,5],[6,6],[7,7],[8,8],[10,10]]
    int maxMachines = 0;
    while (tasksPQ.size() != 0 ) {
        List<Integer> task = tasksPQ.poll(); // [4,4]
        while (machinesPQ.size() > 0) {
            if (task.get(0) > machinesPQ.peek().get(1)) { 
                machinesPQ.poll(); // --
            } else break;
        }
        machinesPQ.offer(task); // [4,4]
        maxMachines = Math.max(maxMachines, machinesPQ.size()); // 1
    }
    
    return maxMachines;
  }
  public static void main(String[] args) {
    List<List<List<Integer>>> inputs = Arrays.asList(
        Arrays.asList(
            Arrays.asList(1, 1),
            Arrays.asList(5, 5),
            Arrays.asList(8, 8),
            Arrays.asList(4, 4),
            Arrays.asList(6, 6),
            Arrays.asList(10, 10),
            Arrays.asList(7, 7)
        ),
        Arrays.asList(
            Arrays.asList(1, 7),
            Arrays.asList(1, 7),
            Arrays.asList(1, 7),
            Arrays.asList(1, 7),
            Arrays.asList(1, 7),
            Arrays.asList(1, 7)
        ),
        Arrays.asList(
            Arrays.asList(1, 7),
            Arrays.asList(8, 13),
            Arrays.asList(5, 6),
            Arrays.asList(10, 14),
            Arrays.asList(6, 7)
        ),
        Arrays.asList(
            Arrays.asList(1, 3),
            Arrays.asList(3, 5),
            Arrays.asList(5, 9),
            Arrays.asList(9, 12),
            Arrays.asList(12, 13),
            Arrays.asList(13, 16),
            Arrays.asList(16, 17)
        ),
        Arrays.asList(
            Arrays.asList(12, 13),
            Arrays.asList(13, 15),
            Arrays.asList(17, 20),
            Arrays.asList(13, 14),
            Arrays.asList(19, 21),
            Arrays.asList(18, 20)
        )
    );

    List<List<List<Integer>>> inputTaskList = new ArrayList<>();
    for(int j = 0; j < inputs.size(); j++) {
        inputTaskList.add(new ArrayList<>());
        for(int k = 0; k < inputs.get(j).size(); k++) {
            inputTaskList.get(j).add(new ArrayList<>(inputs.get(j).get(k)));
        }
    }

    for (int i = 0; i < inputTaskList.size(); i++) {
      System.out.println(i + 1 + ".\tTask = " + inputTaskList.get(i).toString());
      System.out.println("\tOptimal number of machines = " + tasks(inputTaskList.get(i)));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
    
}
