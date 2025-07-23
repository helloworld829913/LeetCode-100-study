package org.study.linkTable;


public class ReverseLinkedList {

    /**
     * 反转链表
     * 迭代方法
     * 思路：
     * 1. 创建next节点对象，存储原有链表剩余部分
     * 2. 将当前节点的next指针指向prev（前节点）节点
     * 3. 将当前节点对象作为前节点，给prev赋值
     * 4. 用next节点对象给curr节点对象赋值，继续遍历
     * prev = curr：将 curr 变量中存储的内存地址（即它指向的 ListNode 对象的地址）复制一份，然后存储到 prev 变量中。
     *
     * @param head 链表头
     * @return 链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // 保存 curr 节点的原始 next 指针所指向的节点。这是为了确保我们不会丢失链表的其余部分，以便在下一步中继续遍历。
            ListNode next = curr.next;
            // 将当前节点的 next 指针指向 prev（即它在原始链表中的前一个节点）。
            curr.next = prev;
            // 这样，在下一次循环中，当前的 curr 节点就成为了下一个节点的“前一个节点”。
            prev = curr;
            // 指向之前保存的 next 节点，准备处理下一个节点。
            curr = next;
        }
        return prev;
    }

    /**
     * 递归版本。
     * @return newHead 指向尾节点的引用。
     * 它的值（指向的内存地址）是不变的，它始终指向原始链表的最后一个节点。这个节点就是反转后链表的头节点。
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 假设只有3层1->2->3->NULL
        // reverseList1(1) 调用 reverseList1(2)
        // reverseList1(2) 调用 reverseList1(3)
        // reverseList1(3)：此时 head 是 3，head.next 是 null。满足 head.next == null 的基本情况。它直接 return head;，也就是 return 3;。
        ListNode newHead = reverseList1(head.next);
        // newHead 现在是 3。
        // 当前 head 是 2，head.next 是 3。
        // head.next.next = head;
        // 3.next = 2; (现在 3 指向 2)
        //链表状态：1 -> 2 (原始) 和 3 -> 2 (新)
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {

        // 创建链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 反转链表
        ReverseLinkedList solution = new ReverseLinkedList();

        // 打印反转后的链表
        ListNode current = solution.reverseList1(head);
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
