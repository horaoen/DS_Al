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
}
