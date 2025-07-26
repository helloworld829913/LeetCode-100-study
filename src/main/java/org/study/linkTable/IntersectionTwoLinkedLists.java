package org.study.linkTable;

import java.util.HashSet;
import java.util.Set;


public class IntersectionTwoLinkedLists {

    /**
     * 判断两个链表有没有相交。
     * 解决思路：创建两个指针，分别指向两个链表，然后同时移动，当两个指针指向同一个节点时，则返回该节点。
     *
     * @param headA 链表A
     * @param headB 链表B
     * @return 返回相交的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 使用双指针解决
     * 1. 首先判断两个链表是否为空，因为只有不为空才能相交
     * 2. 创建两个指针，分别指向两个链表的头结点
     * 3. 遍历两个链表，判断两个指针是否相等，如果相等，则返回该指针
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA, pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    public static void main(String[] args) {
        IntersectionTwoLinkedLists solution = new IntersectionTwoLinkedLists();

        // 测试用例 1: 链表相交
        // listA = 4 -> 1 -> 8 -> 4 -> 5
        // listB = 5 -> 6 -> 1 -> 8 -> 4 -> 5
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        ListNode intersectionNode = solution.getIntersectionNode1(headA, headB);
        System.out.println("测试用例 1 (相交):");
        if (intersectionNode != null) {
            System.out.println("相交节点的值: " + intersectionNode.val); // 预期输出: 8
        } else {
            System.out.println("没有找到相交节点。");
        }

        // 测试用例 2: 链表不相交。
        // listC = 2 -> 6 -> 4
        // listD = 1 -> 5
        ListNode headC = new ListNode(2);
        headC.next = new ListNode(6);
        headC.next.next = new ListNode(4);

        ListNode headD = new ListNode(1);
        headD.next = new ListNode(6);
        headD.next.next = new ListNode(6);

        ListNode noIntersectionNode = solution.getIntersectionNode1(headC, headD);
        // 这里是因为相交节点要求两个链表在内存中指向同一个节点对象（而不仅仅是值相等）。此处所有节点均独立创建，无共享节点，故输出没有找到相交节点。
        System.out.println("\n测试用例 2 (不相交):");
        if (noIntersectionNode != null) {
            System.out.println("相交节点的值: " + noIntersectionNode.val);
        } else {
            System.out.println("没有找到相交节点。"); // 预期输出: 没有找到相交节点。
        }
    }
}
