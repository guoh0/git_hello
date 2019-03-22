import java.util.Stack;

/**
 逆序打印链表
 */
public class LinkReverse {
    /**
     * 我们的链表有很多，单链表，双向链表，环链表等。
     * 这里是最普通的单链表模式，我们一般会在数据存储区域存放数据，然后有一个指针指向下一个结点。
     * 虽然 Java 中没有指针这个概念，但 Java 的引用恰如其分的填补了这个问题。
     * <p>
     * 思路:
     * 逆序输出？栈是一种「后进先出」的数据结构，用栈的原理更好能达到我们的要求
     * <p>
     * 既然可以用栈来实现，我们也极容易想到递归也能解决这个问题，因为递归本质上也就是一个栈结构。
     * 要实现逆序输出链表，我们每访问一个结点的时候，我们先递归输出它后面的结点，再输出该结点本身，这样链表的输出结果自然也是反过来了。
     */

    public static class Node{
        int data; //当前节点 int类型
        Node next; //下一节点
    }

    /**
     * 栈实现
     * @param head
     */
    public static void printLinkReverseOrStack(Node head) {
        Stack<Node> stack = new Stack<>();
        //只要当前的节点不是null 一直往栈里放
        while (head != null) {
            stack.push(head);
            head = head.next;   //和while配合 类似递归
        }
        while (!stack.empty()) {
            System.out.println(stack.pop().data);
        }
    }

    /**
     * 递归实现
     * @param head
     */
    public static void printLinkReverse(Node head) {
        if (head != null) {
            printLinkReverse(head.next);
            System.out.println(head.data); //反向打印
        }
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static Node reverseList(Node head) {
        // 创建一个临时结点，当作尾插法的逻辑头结点
        Node root = new Node();
        // 逻辑头结点点的下一个结点为空
        root.next = null;

        // 用于记录要处理的下一个结点
        Node next;
        // 当前处理的结点不为空
        while (head != null) {
            // 记录要处理的下一个结点
            next = head.next;
            // 当前结点的下一个结点指向逻辑头结点的下一个结点
            head.next = root.next;
            // 逻辑头结点的下一个结点指向当前处理的结点
            root.next = head;
            // 上面操作完成了一个结点的头插

            // 当前结点指向下一个要处理的结点
            head = next;
        }

        // 逻辑头结点的下一个结点就是返回后的头结点
        return root.next;
    }


    public static void main(String[] args) {
        Node head = new Node();
        head.data = 1;
        head.next = new Node();
        head.next.data = 2;
        head.next.next = new Node();
        head.next.next.data = 3;
        head.next.next.next = new Node();
        head.next.next.next.data = 4;
        head.next.next.next.next = new Node();
        head.next.next.next.next.data = 5;
        printLinkReverse(head);
        printLinkReverseOrStack(head);
    }

}
