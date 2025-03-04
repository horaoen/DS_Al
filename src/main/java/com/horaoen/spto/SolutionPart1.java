package com.horaoen.spto;

import java.util.*;

// https://www.nowcoder.com/share/jump/3598551081741057918628
public class SolutionPart1 {
    public int duplicate(int[] numbers) {
        // write code here
        Set<Integer> set = new HashSet<Integer>();
        for (int number : numbers) {
            if (set.contains(number)) {
                return number;
            } else {
                set.add(number);
            }
        }
        return -1;
    }

    // https://www.nowcoder.com/share/jump/3598551081741058112304
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    // https://www.nowcoder.com/share/jump/3598551081741058510280
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (listNode != null && listNode.next != null) {
            res.addAll(printListFromTailToHead(listNode.next));
        }
        if (listNode != null) {
            res.add(listNode.val);
        }
        return res;
    }

    // https://www.nowcoder.com/share/jump/3598551081741058487180
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        // write code here
        if (preOrder.length == 0) {
            return null;
        }
        int root = preOrder[0];

        int vinRootIndex = findIndex(vinOrder, root);
        TreeNode res = new TreeNode(root);

        if (preOrder.length > 1) {
            int[] leftVinOrder = Arrays.copyOfRange(vinOrder, 0, vinRootIndex);
            int[] leftPreOrder = Arrays.copyOfRange(preOrder, 1, vinRootIndex + 1);
            res.left = reConstructBinaryTree(leftPreOrder, leftVinOrder);
        }
        if (vinOrder.length > 1 && preOrder.length > 1) {
            int[] rightVinOrder = Arrays.copyOfRange(vinOrder, vinRootIndex + 1, vinOrder.length);
            int[] rightPreOrder = Arrays.copyOfRange(preOrder, vinRootIndex + 1, vinOrder.length);
            res.right = reConstructBinaryTree(rightPreOrder, rightVinOrder);
        }
        return res;
    }

    private int findIndex(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return 0;
    }

    // https://www.nowcoder.com/share/jump/3598551081741058539489
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        TreeLinkNode root = pNode;
        // 寻找根节点
        while (root.next != null) {
            root = root.next;
        }

        // 中序遍历
        ArrayList<TreeLinkNode> order = new ArrayList<TreeLinkNode>();
        order = inOrder(root);

        for (int i = 0; i < order.size() - 1; i++) {
            if (order.get(i).val == pNode.val) {
                return order.get(i + 1);
            }
        }
        return null;
    }

    private ArrayList<TreeLinkNode> inOrder(TreeLinkNode rootNode) {
        ArrayList<TreeLinkNode> res = new ArrayList<>();
        if (rootNode.left != null) {
            res.addAll(inOrder(rootNode.left));
        }

        res.add(rootNode);
        if (rootNode.right != null) {
            res.addAll(inOrder(rootNode.right));
        }
        return res;
    }

    // https://www.nowcoder.com/share/jump/3598551081741058835713
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    // https://www.nowcoder.com/share/jump/3598551081741058905483
    int[] fib = new int[50];

    public int Fibonacci(int n) {
        // write code here
        if (n <= 2)
            return 1;
        if (fib[n] != 0) {
            return fib[n];
        } else {
            fib[n] = Fibonacci(n - 1) + Fibonacci(n - 2);
            return fib[n];
        }
    }
}
