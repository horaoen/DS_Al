package com.horaoen.spto;

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
}
