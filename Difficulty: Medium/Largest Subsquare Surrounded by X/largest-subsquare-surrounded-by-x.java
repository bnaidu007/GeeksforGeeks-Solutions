class Solution {
    public int largestSubsquare(char[][] mat) {
        int n = mat.length;
        int[][] row = new int[n][n];
        int[][] col = new int[n][n];

        // Step 1: Precompute consecutive 'X's horizontally and vertically
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 'X') {
                    row[i][j] = (j == 0) ? 1 : row[i][j - 1] + 1;
                    col[i][j] = (i == 0) ? 1 : col[i - 1][j] + 1;
                } else {
                    row[i][j] = 0;
                    col[i][j] = 0;
                }
            }
        }

        int maxSide = 0;

        // Step 2: Iterate from bottom-right corner to top-left corner
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Potential side length bounded by bottom and right sides
                int k = Math.min(row[i][j], col[i][j]);

                // Check if top and left sides can form a square of size k
                while (k > maxSide) {
                    if (row[i - k + 1][j] >= k && col[i][j - k + 1] >= k) {
                        maxSide = k;
                        break;
                    }
                    k--;
                }
            }
        }

        return maxSide;
    }
}