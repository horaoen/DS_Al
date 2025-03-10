package com.horaoen.spto;

import java.util.HashSet;
import java.util.Set;

public class SolutionPart3 {
    // https://www.nowcoder.com/share/jump/3598551081741156625780
    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here
        if (k <= 0)
            return null;
        int nodeCount = 0;
        ListNode node = pHead;
        while (node != null) {
            nodeCount++;
            node = node.next;
        }

        if (k > nodeCount)
            return null;
        k = nodeCount - k;

        int i = 0;
        while (i < k) {
            pHead = pHead.next;
            i++;
        }
        return pHead;
    }

    // https://www.nowcoder.com/share/jump/3598551081741157296700
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        Set<ListNode> set = new HashSet<>();
        while (pHead != null) {
            if (set.contains(pHead)) {
                return pHead;
            }
            set.add(pHead);
            pHead = pHead.next;
        }

        return null;
    }

    // https://www.nowcoder.com/share/jump/3598551081741168851695
    public ListNode ReverseList(ListNode head) {
        // write code here
        ListNode preNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }

    // https://www.nowcoder.com/share/jump/3598551081741502263088
    public ListNode Merge(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null && pHead2 == null)
            return null;
        if (pHead1 == null)
            return pHead2;
        if (pHead2 == null)
            return pHead1;
        // write code here
        if (pHead1.val < pHead2.val) {
            pHead1.next = Merge(pHead1.next, pHead2);
            return pHead1;
        } else {
            pHead2.next = Merge(pHead1, pHead2.next);
            return pHead2;
        }
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    // https://www.nowcoder.com/share/jump/3598551081741573209868
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null || (root1 == null && root2 != null))
            return false;
        return isSame(root1, root2) || HasSubtree(root1.left, root2) ||
                HasSubtree(root1.right, root2);
    }

    public boolean isSame(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        else if (root1 != null && root1.val == root2.val) {
            return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);

        }
        return false;
    }
}
