package org.study.linkTable;

import java.util.HashSet;
import java.util.Set;

public class CycleLinkedList {

    /**
     * 判断链表中是否有环
     * 方法1：哈希表
     * 思路：遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
     * 使用哈希表来存储所有已经访问过的节点。
     * 如果该节点已经存在于哈希表中，
     * 则说明该链表是环形链表，否则就将该节点加入哈希表中。
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            // 如果add成功，add返回true，那么if里面就是false，就不会执行return true的命令。
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        // 全部都添加成功，说明没有环
        return false;
    }

    /**
     * 方法2：快慢指针
     * 原理：当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     * 说明：如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
     * 等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，
     * 即套了「乌龟」若干圈。
     * 初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，
     * 就说明该链表为环形链表。
     * 否则快指针将到达链表尾部，该链表不为环形链表。
     * 最后：如果没有环，它们永远不会相等。如果有环，它们最终一定会相等。
     * 简单的理解slow追上fast，无法很好的判断是否追上，最好的判断就是slow==fast
     * slow.next=fast，会造成判断不准确。
     */
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        // 创建链表节点
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        // 构建链表连接关系
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 创建环：-4 -> 2
        node4.next = node2;

        CycleLinkedList cycleLinkedList = new CycleLinkedList();

        // 使用哈希表方法判断是否有环
        boolean hasCycle1Result = cycleLinkedList.hasCycle(node1);
        System.out.println("Has cycle (method 1): " + hasCycle1Result);

        // 使用快慢指针方法判断是否有环
        boolean hasCycle2Result = cycleLinkedList.hasCycle1(node1);
        System.out.println("Has cycle (method 2): " + hasCycle2Result);
    }
}
