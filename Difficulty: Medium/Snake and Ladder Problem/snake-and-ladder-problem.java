import java.util.*;

 class Solution {
     public int minThrows(int n, int[] lad, int[] sn) {
         int target = n * n;
         int[] board = new int[target + 1];

         // Map default cell values
         for (int i = 1; i <= target; i++) {
             board[i] = i;
         }

         // Populate ladder shortcuts
         for (int i = 0; i < lad.length; i += 2) {
             board[lad[i]] = lad[i + 1];
         }

         // Populate snake shortcuts
         for (int i = 0; i < sn.length; i += 2) {
             board[sn[i]] = sn[i + 1];
         }

         // BFS Queue stores arrays of {current_cell, moves_count}
         Queue<int[]> queue = new LinkedList<>();
         boolean[] visited = new boolean[target + 1];

         queue.add(new int[]{1, 0});
         visited[1] = true;

         while (!queue.isEmpty()) {
             int[] current = queue.poll();
             int cell = current[0];
             int moves = current[1];

             if (cell == target) {
                 return moves;
             }

             // Try all possible dice throws from 1 to 6
             for (int dice = 1; dice <= 6; dice++) {
                 int next = cell + dice;

                 if (next <= target) {
                     int destination = board[next];

                     if (!visited[destination]) {
                         visited[destination] = true;
                         queue.add(new int[]{destination, moves + 1});
                     }
                 }
             }
         }

         return -1;
     }
 }