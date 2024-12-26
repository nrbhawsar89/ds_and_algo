public class RemoveNthNode {

  public static LinkedListNode removeNthLastNode(LinkedListNode head, int n) {
    if (head == null) return null;
    LinkedListNode left = head;
    LinkedListNode right = head;
    for (int i = 0; i < n;i ++) {
      if (right == null) return head.next;
      right = right.next;
    }
    while (right.next != null) {
      left = left.next;
      right = right.next;
    }
    left.next = left.next.next;
    return head;
  }
    public static void main(String[] args) {
        int[][] inputs = {
          {23, 89, 10, 5, 67, 39, 70,28},
          {34, 53, 6, 95, 38, 28, 17, 63, 16, 76},
          {288, 224, 275, 390, 4, 383, 330, 60, 193},
          {1, 2, 3, 4, 5, 6, 7, 8, 9},                       
          {69, 8, 49, 106, 116, 112, 104, 129, 39, 14, 27, 12}
        };
    
        int[] n = {4, 1, 6, 9, 11};
        RemoveNthNode rn = new RemoveNthNode();
        for (int i = 0; i < inputs.length; i++) {
          LinkedList inputLinkedList = rn.new LinkedList ();
          inputLinkedList.createLinkedList(inputs[i]);
          System.out.print((i + 1) + ".\tLinked List:\t\t");
          PrintList.printListWithForwardArrow(inputLinkedList.head);
          System.out.print("\n\tn = " + n[i]);
          System.out.print("\n\tUpdated Linked List:\t");
          PrintList.printListWithForwardArrow(removeNthLastNode(inputLinkedList.head, n[i]));
          System.out.println();
          System.out.println(new String(new char[100]).replace('\0', '-'));
        }
      }

      private static class PrintList {
        private static void printListWithForwardArrow(LinkedListNode head) {
            while (head.next != null) {
                System.out.print(head.data);
                System.out.print("->");
                head = head.next;
            }
            System.out.print("NULL");
        }
      }

      private class LinkedListNode {
        private int data;
        private LinkedListNode next;
        LinkedListNode (int data) {
            this.data = data;
            next = null;
        }
      }

      private class LinkedList {
        LinkedListNode head;

        public LinkedListNode createLinkedList(int[] arr) {
            if (arr.length == 0) return null;
            head = new LinkedListNode(arr[0]);
            LinkedListNode curr = head;
            for (int i = 1; i < arr.length; i ++) {
                LinkedListNode node = new LinkedListNode(arr[i]);
                curr.next = node;
                curr = curr.next;
            }
            return head;
        }
      }
    
}
