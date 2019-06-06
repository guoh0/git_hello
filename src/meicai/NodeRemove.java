package meicai;

public class NodeRemove {
    static class ListNode {
        int val;
        ListNode next;
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode p = head;
        while (p.next != null) {
            if (p.val == val) {
                p.val = p.next.val;
                p.next = p.next.next;
            } else if (p.next.next !=null && p.next.next.val == val){
                p.next.next = p.next.next.next;
            }
            p = p.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 6;
        head.next = new ListNode();
        head.next.val = 2;
        head.next.next = new ListNode();
        head.next.next.val = 6;
        head.next.next.next = new ListNode();
        head.next.next.next.val = 3;
        head.next.next.next.next = new ListNode();
        head.next.next.next.next.val = 4;
        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.val = 5;
        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.val = 6;
        ListNode listNode = removeElements(head, 6);
        print(listNode);
//        print(head);
    }

    public static void print(ListNode listNode) {
        listNode = listNode.next;
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
