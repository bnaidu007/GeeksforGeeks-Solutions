class Solution {
    public long pairAndSum(int[] arr) {
        long totalSum = 0;

        // Iterate through all 32 bit positions
        for (int i = 0; i < 32; i++) {
            long count = 0;

            // Count how many numbers have the i-th bit set
            for (int num : arr) {
                if ((num & (1 << i)) != 0) {
                    count++;
                }
            }

            // Total pairs with i-th bit set = count * (count - 1) / 2
            long pairs = (count * (count - 1)) / 2;

            // Add value contribution of the i-th bit to totalSum
            totalSum += pairs * (1L << i);
        }

        return totalSum;
    }
}