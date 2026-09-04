class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {

        int n = arr.size();

        if (m >= n) {
            int total = 0;

            for (int num : arr) {
                total += num;
            }

            return total;
        }

        int windowSum = 0;

        // First window
        for (int i = 0; i < m; i++) {
            windowSum += arr.get(i);
        }

        int maxSum = windowSum;

        // Circular Sliding Window
        for (int i = 1; i < n; i++) {

            // Remove previous starting element
            windowSum -= arr.get(i - 1);

            // Add next circular element
            windowSum += arr.get((i + m - 1) % n);

            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}