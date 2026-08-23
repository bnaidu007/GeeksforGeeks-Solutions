import java.util.*;

class Solution {
    static class Node {
        int r, c, u, d;
        Node(int r, int c, int u, int d) {
            this.r = r;
            this.c = c;
            this.u = u; // up moves used so far
            this.d = d; // down moves used so far
        }
    }

    public int numberOfCells(int r, int c, int u, int d, char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Base case: starting cell is an obstacle
        if (mat[r][c] == '#') return 0;

        // Store min (up_moves, down_moves) spent to reach each cell
        int[][] minUp = new int[n][m];
        int[][] minDown = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(minUp[i], Integer.MAX_VALUE);
            Arrays.fill(minDown[i], Integer.MAX_VALUE);
        }

        // Queue for BFS
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r, c, 0, 0));

        minUp[r][c] = 0;
        minDown[r][c] = 0;

        int visitedCount = 0;
        boolean[][] visited = new boolean[n][m];

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (!visited[curr.r][curr.c]) {
                visited[curr.r][curr.c] = true;
                visitedCount++;
            }

            // Direction vectors: Up, Down, Left, Right
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                // Check grid boundaries and obstacles
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || mat[nr][nc] == '#') {
                    continue;
                }

                int nextU = curr.u + (i == 0 ? 1 : 0);
                int nextD = curr.d + (i == 1 ? 1 : 0);

                // Ensure within user limit
                if (nextU <= u && nextD <= d) {
                    // Update if we found a path with fewer up or down moves
                    if (nextU < minUp[nr][nc] || nextD < minDown[nr][nc]) {
                        minUp[nr][nc] = Math.min(minUp[nr][nc], nextU);
                        minDown[nr][nc] = Math.min(minDown[nr][nc], nextD);
                        q.add(new Node(nr, nc, nextU, nextD));
                    }
                }
            }
        }

        return visitedCount;
    }
}