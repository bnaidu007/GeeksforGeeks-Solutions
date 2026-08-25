class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        // Code here
        int n =arr.length;
       int windowSum = 0;
       for (int i = 0; i < k; i++) {
           windowSum += arr[i];
       }

       int maxSum = windowSum;

       // Step 2: slide the window across the rest of the array
       for (int i = k; i < n; i++) {
           windowSum += arr[i] - arr[i - k];
           maxSum = Math.max(maxSum, windowSum);
       }

       return maxSum;
    }
}