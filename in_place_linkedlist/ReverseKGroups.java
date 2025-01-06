package in_place_linkedlist;

public class ReverseKGroups {
    class LinkedListNode {
        public int data;
        public LinkedListNode next;
    
        // Constructor will be used to make a LinkedListNode type object
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    
    }
    class LinkedList<T> {
        public LinkedListNode head;
    
        // constructor will be used to make a LinkedList type object
        public LinkedList() {
            this.head = null;
        }
    
        // insertNodeAtHead method will insert a LinkedListNode at head
        // of a linked list.
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }
    
        // createLinkedList method will create the linked list using the
        // given integer array with the help of InsertAthead method.
        public void createLinkedList(int[] lst) {
            for (int i = lst.length - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst[i]);
                insertNodeAtHead(newNode);
            }
        }
    }
    public static LinkedListNode reverseKGroups(LinkedListNode head, int k) {
        ReverseKGroups rk = new ReverseKGroups();
        //[1,2,3,4,5]
        // k = 2
        int N = 0;
         LinkedListNode curr = head; // 1
         while (curr != null) {
           curr = curr.next;//null
           N++;//5
         }
         LinkedListNode tmpHead = rk.new LinkedListNode(0); // 0
         tmpHead.next = head; // 0->1->...
         LinkedListNode nextHead = head; // 1->...
         int runs = N/k; // 2
         LinkedListNode tail = tmpHead; // 0
         while (runs != 0) {//2
           LinkedListNode currHead = nextHead; // 3->...
           for (int i = 0; i < k; i ++) { // 2
             nextHead = nextHead.next; // 5->..
           }
           LinkedListNode newHead = getReversedHead(currHead, k); //  3->.., 2// 4->3->null
           tail.next = newHead; // 0->2->(1)->4->3->null
          // for (int i = 0; i < k; i ++) {//1
          //   tail = tail.next; //3
          // }
           tail = currHead; // 3->..
          runs --; // 0
         }
         tail.next = nextHead;
                return tmpHead.next;
    }
         
         private static LinkedListNode getReversedHead(LinkedListNode head, int k) { //3->.., 2
           LinkedListNode prev = head; // 3->..
           LinkedListNode curr = head.next; // 4->..
           prev.next = null; // 3->null
           while (k != 1) { //1
             LinkedListNode tmp = curr.next; // 5->..
             curr.next = prev; // 4->3->null..
             prev = curr; // 4
             curr = tmp; // 5
             k--; // 1
           }
           return prev; // 4->3->null
         }
         
}
