public class ReverseLinkedList {
     public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
      //left = 2
      //right = 4
    // 0->1->2->3->4->5->null
    // 2 = curr 
    // 
    if (left == right) return head;
    if (head == null || head.next == null) return head;
    LinkedListNode preHead = new LinkedListNode(0);
    preHead.next = head;
    int pos = 0;
    LinkedListNode curr = preHead; // 0

    while (curr != null && curr.next != null) { // 1
      if (pos == left -1) { // 1 == 1
         LinkedListNode tail = curr; // 1
         LinkedListNode prev = curr.next; // 2
         LinkedListNode tmpHead = prev; // 2
         curr = curr.next.next; // 3
         pos ++; // 2
         while (pos != right) { //4!=4
           LinkedListNode tmp = curr.next; // 5
           curr.next = prev; // 4->3->2
           prev = curr; // 4
           curr = tmp; // 5
           pos ++; // 4
         }
         tmpHead.next = curr;// 4->3->(2)->5
         tail.next = prev; // (1))->4->3->2->5
      } else {
        curr = curr.next;
      }
      pos ++; // 5
    }
    return preHead.next;
  }
}
