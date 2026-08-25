class Solution {
    public int minMoves(int[] arr) {
        // code here
        int n = arr.length;
        int[] pos = new int[n + 1];  // pos[value] = index in arr

        for (int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }

        int maxChain = 1;
        int currentChain = 1;

        for (int v = 2; v <= n; v++) {
            if (pos[v] > pos[v - 1]) {
                currentChain++;
            } else {
                currentChain = 1;
            }
            maxChain = Math.max(maxChain, currentChain);
        }

        return n - maxChain;
    }
}