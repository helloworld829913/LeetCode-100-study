package org.study.linkTable;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoSortedList {

    /**
     * 方法1：递归
     * <p>
     * 思路：<br>
     * 1. 确定当前合并链表的头节点：在两个链表的当前头节点中，选择值较小的那一个作为合并后链表的头节点。<br>
     * 2. 递归处理剩余部分：将选定头节点的 next 指针指向对剩余链表（即选定头节点所在链表的剩余部分，以及另一个完整链表）进行合并的结果。<br>
     * 3. 基本情况（Base Cases）：当其中一个链表为空时，另一个链表就是合并后的结果。<br>
     * <p>
     * 工作流程：
     * 假设 l1 = [1, 3, 5] 和 l2 = [2, 4, 6]。
     * <p>
     * mergeTwoLists([1,3,5], [2,4,6])
     * <p>
     * 1 < 2，所以选择 l1 (节点1)。
     * l1.next (节点3) 将被设置为 mergeTwoLists([3,5], [2,4,6]) 的结果。
     * 返回 l1 (节点1)。
     * 现在，我们进入 mergeTwoLists([3,5], [2,4,6])
     * <p>
     * 2 < 3，所以选择 l2 (节点2)。
     * l2.next (节点4) 将被设置为 mergeTwoLists([3,5], [4,6]) 的结果。
     * 返回 l2 (节点2)。
     * 现在，我们进入 mergeTwoLists([3,5], [4,6])
     * <p>
     * 3 < 4，所以选择 l1 (节点3)。
     * l1.next (节点5) 将被设置为 mergeTwoLists([5], [4,6]) 的结果。
     * 返回 l1 (节点3)。
     * 现在，我们进入 mergeTwoLists([5], [4,6])
     * <p>
     * 4 < 5，所以选择 l2 (节点4)。
     * l2.next (节点6) 将被设置为 mergeTwoLists([5], [6]) 的结果。
     * 返回 l2 (节点4)。
     * 现在，我们进入 mergeTwoLists([5], [6])
     * <p>
     * 5 < 6，所以选择 l1 (节点5)。
     * l1.next (null) 将被设置为 mergeTwoLists(null, [6]) 的结果。
     * 返回 l1 (节点5)。
     * 现在，我们进入 mergeTwoLists(null, [6])
     * <p>
     * l1 为 null，返回 l2 (节点6)。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            // 如果 l1 链表已经为空，这意味着 l1 中没有更多的节点可以参与合并了。
            // 直接返回 l2。因为 l2 链表本身就是有序的，并且 l1 已经耗尽，所以 l2 的剩余部分就是合并后的结果。
            return l2;
        } else if (l2 == null) {
            // 与上一个情况类似
            return l1;
        } else if (l1.val < l2.val) {
            // 当 l1 和 l2 都不为空时，我们需要比较它们的当前头节点的值。
            // 如果 l1 的头节点值小于 l2 的头节点值，
            // 那么 l1 的当前节点（l1）就应该是合并后链表的头节点。
            l1.next = mergeTwoLists(l1.next, l2);
            // 将l1.next设置成mergeTwoLists(l1.next, l2)的结果
            // 也就是l1.next和l2中，递归后的结果。
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    /**
     * 方法2：迭代
     * <p>
     * 思路：其实创建一个新链表prehead，然后创建一个prev指针。
     * 用prev指针，完成对prehead链表的修改。
     * prev是修改prehead链表的链表结构的工具。
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        // 如果没有它，我们在循环的第一次迭代时就需要写额外的代码来确定哪个节点是新链表的头
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // 如果 l1 的节点值更小（或相等）。
                // 将新链表的尾部（prev）指向 l1 的当前节点。

                // 当执行 prev.next = l1 或 prev.next = l2 时
                // 实际上是通过 prev 引用找到了它指向的对象,然后修改了那个对象的 next 指针
                // 这确实会改变链表结构（因为对象被修改了）
                prev.next = l1;
                l1 = l1.next; // l1 指针向后移动一位，准备下一轮比较。
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            // prev = prev.next 只改变引用本身：
            // 这个操作不修改任何对象
            // 它只是把 prev 这个引用变量重新赋值，让它指向另一个对象
            prev = prev.next;// 更新 prev 指针，让它移动到这个新添加的节点上，从而继续保持 prev 指向新链表的最后一个节点。
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        // 当 while 循环结束时，意味着 l1 或 l2 中至少有一个已经为 null。
        // 因为两个链表都是有序的,我们不需要再逐个比较了，直接将新链表的末尾（prev.next）指向那个还未合并完的链表即可。
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    public static void main(String[] args) {
        // 创建两个链表用于测试
        // 链表1: 1 -> 2 -> 4
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // 链表2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // 合并两个链表
        MergeTwoSortedList merger = new MergeTwoSortedList();
        ListNode mergedList = merger.mergeTwoLists1(l1, l2);

        // 打印合并后的链表
        System.out.print("Merged List: ");
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
        System.out.println();
    }
}
