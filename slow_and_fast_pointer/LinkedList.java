public class LinkedList {
    LinkedListNode head;
    public LinkedList(LinkedListNode head) {
        this.head = head;
    }

    public void insertAtHead(LinkedListNode node) {
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public void insertAtEnd(LinkedListNode node) {
        LinkedListNode tmp = head;
        while (head.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public static  LinkedListNode createLinkedList(int[] arr) {
        if (arr.length ==0) return null;
        LinkedListNode head = new LinkedListNode();
        head.data = arr[0];
        LinkedListNode tmp = head;
        for (int i = 1; i < arr.length; i ++) {
            LinkedListNode node = new LinkedListNode();
            node.data = arr[i];
            tmp.next = node;
            tmp = node;
        }
        return head;
    }

    public LinkedListNode getNode(LinkedListNode head, int pos) {
        while (pos != 0 && head != null) {
            head = head.next;
            pos --;
        }
        return head;
    }


}
