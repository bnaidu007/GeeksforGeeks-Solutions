import java.util.Arrays;

class Solution {
    private int[][][] dp;

    public int minCount(int[] arr) {
        int n = arr.length;
        // dp[i][inc][dec] stores the min unused elements starting from index i
        // inc ranges from 0 to 100
        // dec ranges from 0 to 101 (101 represents infinity / unassigned)
        dp = new int[n][101][102];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        return solve(0, 0, 101, arr);
    }

    private int solve(int i, int inc, int dec, int[] arr) {
        if (i == arr.length) {
            return 0;
        }
        if (dp[i][inc][dec] != -1) {
            return dp[i][inc][dec];
        }

        int val = arr[i];

        // Choice 1: Leave element unused
        int ans = 1 + solve(i + 1, inc, dec, arr);

        // Choice 2: Add to strictly increasing subsequence
        if (val > inc) {
            ans = Math.min(ans, solve(i + 1, val, dec, arr));
        }

        // Choice 3: Add to strictly decreasing subsequence
        if (val < dec) {
            ans = Math.min(ans, solve(i + 1, inc, val, arr));
        }

        return dp[i][inc][dec] = ans;
    }
}