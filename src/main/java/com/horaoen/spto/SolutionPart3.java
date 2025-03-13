package com.horaoen.spto;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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

    // https://www.nowcoder.com/share/jump/3598551081741573512833
    public TreeNode Mirror(TreeNode pRoot) {
        if (pRoot == null)
            return null;
        TreeNode tmpNode = pRoot.left;
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = Mirror(tmpNode);
        return pRoot;
    }

    // https://www.nowcoder.com/share/jump/3598551081741577723549
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        // 循环右下左上
        int[] x = { 0, 1, 0, -1 };
        int[] y = { 1, 0, -1, 0 };

        boolean[][] f = new boolean[matrix.length][matrix[0].length];
        int index = 0;
        int i = 0, j = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (i < matrix.length && i >= 0 && j >= 0 && j < matrix[0].length && !f[i][j]) {
            res.add(matrix[i][j]);
            f[i][j] = true;
            int count = 0;
            int nextx = i + x[index];
            int nexty = j + y[index];
            while (((nextx < 0 || nexty < 0 || nextx >= matrix.length || nexty >= matrix[0].length) || f[nextx][nexty])
                    &&
                    count < 4) {
                index = (index + 1) % 4;
                nextx = i + x[index];
                nexty = j + y[index];
                count++;
            }
            i = nextx;
            j = nexty;
        }
        return res;
    }

    // https://www.nowcoder.com/share/jump/3598551081741611471931
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty() || node <= stack2.peek()) {
            stack2.push(node);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }

    // https://www.nowcoder.com/share/jump/3598551081741616688524
    public boolean IsPopOrder(int[] pushV, int[] popV) {
        // write code here
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        for (int i = 0; i < popV.length; i++) {
            while (index < pushV.length && (stack.isEmpty() || stack.peek() != popV[i])) {
                stack.push(pushV[index]);
                index++;
            }
            if (stack.peek() == popV[i]) {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    // https://www.nowcoder.com/share/jump/3598551081741617734781
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        // bfs
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        if (root == null)
            return res;
        deque.add(root);

        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            res.add(node.val);
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
        return res;
    }

    // https://www.nowcoder.com/share/jump/3598551081741749348917
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0)
            return false;
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = sequence.length - 1; i >= 0; i--) {
            if (sequence[i] > root)
                return false;
            while (!stack.isEmpty() && stack.peek() > sequence[i]) {
                root = stack.pop();
            }
            stack.push(sequence[i]);
        }
        return true;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    // https://www.nowcoder.com/share/jump/3598551081741751131409
    public RandomListNode Clone(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode tail = dummy, tmp = head;
        while (tmp != null) {
            RandomListNode node = new RandomListNode(tmp.label);
            map.put(tmp, node);
            tail.next = node;
            tail = tail.next;
            tmp = tmp.next;
        }
        tail = dummy.next;
        while (head != null) {
            if (head.random != null)
                tail.random = map.get(head.random);
            tail = tail.next;
            head = head.next;
        }
        return dummy.next;
    }
}
