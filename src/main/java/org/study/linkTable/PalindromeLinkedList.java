package org.study.linkTable;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

    /**
     * 判断是否是回文链表
     * 方法1：双指针
     * 思路：用一个链表存储节点数据。然后用双指针判断列表的值是否相等。
     * @param head 头节点
     * @return 是/否
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals=new ArrayList<>();
        ListNode currentNode=head;
        while (currentNode!=null){
            vals.add(currentNode.val);
            currentNode=currentNode.next;
        }

        // 使用双指针判断是否回文
        // 比较前端元素和后端元素是否相等
        // 注意：这里使用 .equals() 而不是 ==，因为 Integer 是对象类型，
        // 对于对象比较，.equals() 才是正确的比较值的方法。
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 方法2：递归
     * 缺点：在许多语言中，堆栈帧的开销很大（如 Python），
     * 并且最大的运行时堆栈深度为 1000（可以增加，但是有可能导致底层解释程序内存出错）
     */
    public boolean isPalindrome1(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    // 为了让 frontPointer 在整个递归过程中保持其状态并被所有递归调用共享和修改，它必须是一个共享状态。
    // 类的成员变量
    // 其他方法，比如作为局部变量，修改的只是某一个栈中的值，上一个栈的值并没有修改，始终指向head节点。
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        // 递归的终止条件：当 currentNode 为 null 时，表示已经到达链表的末尾（或越过末尾）。
        if (currentNode != null) {
            // 这一行使得递归调用不断深入，直到 currentNode 变为 null（链表末尾）。
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    /**
     * 方法3：快慢指针
     * 整个流程可以分为以下五个步骤：
     * 1. 找到前半部分链表的尾节点。
     *  使用快慢指针在一次遍历中找到：慢指针一次走一步，
     *  快指针一次走两步，快慢指针同时出发。
     *  当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     * 2. 反转后半部分链表。（使用反转链表）
     * 3. 判断是否回文。
     * 4. 恢复链表。（再用一次反转链表）
     * 5. 返回结果。
     *
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        // 例如：1->2->3->2->1，firstHalfEnd 是 3
        // 例如：1->2->2->1，firstHalfEnd 是 2 (第一个2)
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // 反转后半部分链表
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head; // 指向链表头部
        ListNode p2 = secondHalfStart; // 指向反转后的后半部分头部
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public static void main(String[] args) {
    // 创建测试用例的链表结构
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(2);
    head.next.next.next.next = new ListNode(1);

    // 创建类实例并调用判断回文链表的方法
    PalindromeLinkedList solution = new PalindromeLinkedList();
    boolean result = solution.isPalindrome2(head);

    // 输出测试结果
    System.out.println("链表是否是回文: " + result);
    }
}
