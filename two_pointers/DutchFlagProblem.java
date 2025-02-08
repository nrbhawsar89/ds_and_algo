public class DutchFlagProblem {
    public static int[] sortColors (int[] colors) { // 0, 1, 1, 0, 1, 2, 2,

        int start = 0;
        int current = 0;
        int end = colors.length - 1; // 6
        while (current <= end ) { // 0 < 5
            if (colors[current] == 0) {
                swap(colors, start, current);
                current ++;
                start ++;
            } else if (colors[current] == 1) {
                current ++;
            } else  {
               swap(colors, current, end);
 
               end --;
            }
        }

        return colors;
    }

    public static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[][] inputs = {
            {0, 1, 0},
            {1, 1, 0, 2},
            {2, 1, 1, 0, 0},
            {2, 2, 2, 0, 1, 0},
            {2, 1, 1, 0, 1, 0, 2}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println();

            int[] sortedColors = sortColors(inputs[i].clone());
            for (int j = 0; j < sortedColors.length; j++) {
                System.out.print(sortedColors[j] + ", ");
            }
            

            
        }
    }
}