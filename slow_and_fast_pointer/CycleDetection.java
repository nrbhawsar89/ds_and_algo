import java.util.LinkedListNode;
public CycleDetection {
    public static boolean detectCycle(LinkedListNode head) {
        if (head == null || head.next == null) return false;
        LinkedListNode slow = head;
        LinkedListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
      }
}
