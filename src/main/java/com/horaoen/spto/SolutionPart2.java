package com.horaoen.spto;

import java.util.*;

public class SolutionPart2 {
    // https://www.nowcoder.com/share/jump/3598551081741068608474
    // Z11 旋转数组的最小数字
    // 二分查找
    public int minNumberInRotateArray(int[] nums) {
        // write code here
        // 找到第一个讲叙数字
        int left = 0, right = nums.length - 1;
        while (left != right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }
        return nums[left];
    }

    // https://www.nowcoder.com/share/jump/3598551081741072434538
    public boolean hasPath(char[][] matrix, String word) {
        // write code here
        // loop matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dfs(matrix, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] matrix, String word, int i, int j, int index) {
        // 判断数组边界
        if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || index > word.length() - 1)
            return false;

        if (index == word.length() - 1) {
            return matrix[i][j] == word.charAt(index);
        } else {
            boolean res = false;
            if (matrix[i][j] == word.charAt(index)) {
                char tmp = matrix[i][j];
                matrix[i][j] = '.';
                res |= dfs(matrix, word, i + 1, j, index + 1);
                res |= dfs(matrix, word, i, j + 1, index + 1);
                res |= dfs(matrix, word, i - 1, j, index + 1);
                res |= dfs(matrix, word, i, j - 1, index + 1);
                matrix[i][j] = tmp;
                return res;
            } else {
                return false;
            }
        }
    }

    int[] countThresholdMap = new int[110];

    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] f = new boolean[110][110];
        for (int i = 0; i < countThresholdMap.length; i++)
            System.out.print(countThresholdMap[i]);
        return dfsMoving(f, threshold, rows, cols, 0, 0);
    }

    public int dfsMoving(boolean[][] f, int threshold, int rows, int cols, int i,
            int j) {
        // 判断数组边界
        if (i < 0 || j < 0 || i >= rows || j >= cols ||
                (countThreshold(i) + countThreshold(j) > threshold) || f[i][j])
            return 0;

        f[i][j] = true;
        return 1 + dfsMoving(f, threshold, rows, cols, i + 1, j)
                + dfsMoving(f, threshold, rows, cols, i - 1, j)
                + dfsMoving(f, threshold, rows, cols, i, j + 1)
                + dfsMoving(f, threshold, rows, cols, i, j - 1);

    }

    public int countThreshold(int i) {
        if (countThresholdMap[i] != 0) {
            return countThresholdMap[i];
        }
        int res = 0;
        int tmp = i;
        while (i > 0) {
            res += (i % 10);
            i /= 10;
        }
        countThresholdMap[tmp] = res;
        return res;
    }

    // https://www.nowcoder.com/share/jump/3598551081741094378105
    public int cutRope(int n) {
        // write code here
        int[] ans = new int[70];
        ans[1] = 1;
        ans[2] = 2;
        ans[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, ans[j] * ans[i - j]);
            }
            ans[i] = max;
        }
        return ans[n];
    }

    // https://www.nowcoder.com/share/jump/3598551081741094864602
    public int NumberOf1(int n) {
        int ans = 0;

        for (int i = 1; i <= 32; i++) {
            if ((n & (1 << i)) != 0) {
                ans++;
            }
        }
        return ans;
    }

    // https://www.nowcoder.com/share/jump/3598551081741096868394
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double ans = base;
        for (int i = 2; i <= exponent; i++) {
            ans *= base;
        }
        return ans;
    }

    // https://www.nowcoder.com/share/jump/3598551081741098671492
    public int[] printNumbers(int n) {
        int max = 1;
        while (n > 0) {
            max *= 10;
            n--;
        }
        int[] ans = new int[max - 1];
        for (int i = 0; i < max - 1; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }

    // https://www.nowcoder.com/share/jump/3598551081741099013438
    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        // write code here

        if (head.val == val) {
            return head.next;
        } else {
            head.next = deleteNode(head.next, val);
        }
        return head;
    }

    // https://www.nowcoder.com/share/jump/3598551081741154229657
    public boolean isNumeric(String str) {
        List<Map<String, Integer>> states = new ArrayList<>();

        // 初始化状态0
        Map<String, Integer> state0 = new HashMap<>();
        state0.put(" ", 0);
        state0.put("s", 1);
        state0.put("d", 2);
        state0.put(".", 4);
        states.add(state0);

        // 初始化状态1
        Map<String, Integer> state1 = new HashMap<>();
        state1.put("d", 2);
        state1.put(".", 4);
        states.add(state1);

        // 初始化状态2
        Map<String, Integer> state2 = new HashMap<>();
        state2.put("d", 2);
        state2.put(".", 3);
        state2.put("e", 5);
        state2.put(" ", 8);
        states.add(state2);

        // 初始化状态3
        Map<String, Integer> state3 = new HashMap<>();
        state3.put("d", 3);
        state3.put("e", 5);
        state3.put(" ", 8);
        states.add(state3);

        // 初始化状态4
        Map<String, Integer> state4 = new HashMap<>();
        state4.put("d", 3);
        states.add(state4);

        // 初始化状态5
        Map<String, Integer> state5 = new HashMap<>();
        state5.put("s", 6);
        state5.put("d", 7);
        states.add(state5);

        // 初始化状态6
        Map<String, Integer> state6 = new HashMap<>();
        state6.put("d", 7);
        states.add(state6);

        // 初始化状态7
        Map<String, Integer> state7 = new HashMap<>();
        state7.put("d", 7);
        state7.put(" ", 8);
        states.add(state7);

        // 初始化状态8
        Map<String, Integer> state8 = new HashMap<>();
        state8.put(" ", 8);
        states.add(state8);

        int currentState = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String transition;
            if (Character.isDigit(c)) {
                transition = "d";
            } else if (c == '+' || c == '-') {
                transition = "s";
            } else if (c == 'e' || c == 'E') {
                transition = "e";
            } else if (c == '.' || c == ' ') {
                transition = String.valueOf(c);
            } else {
                transition = "?";
            }

            if (!states.get(currentState).containsKey(transition)) {
                return false;
            }
            currentState = states.get(currentState).get(transition);
        }

        return currentState == 2 || currentState == 3 || currentState == 7 ||
                currentState == 8;
    }

    // https://www.nowcoder.com/share/jump/3598551081741155833184
    public int[] reOrderArray(int[] array) {
        // write code here
        int index = 0;
        int[] array1 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                array1[index] = array[i];
                index++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                array1[index] = array[i];
                index++;
            }
        }
        return array1;
    }

}
