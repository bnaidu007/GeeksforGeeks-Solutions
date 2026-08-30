import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        int n = l.length;
        long[] prefixRank = new long[n];

        // Build prefix sums of counts in each interval
        long currentTotal = 0;
        for (int i = 0; i < n; i++) {
            currentTotal += (long)(r[i] - l[i] + 1);
            prefixRank[i] = currentTotal;
        }

        ArrayList<Integer> result = new ArrayList<>(rank.length);

        // Process each target rank using binary search
        for (int k : rank) {
            int low = 0, high = n - 1;
            int intervalIndex = -1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixRank[mid] >= k) {
                    intervalIndex = mid;
                    high = mid - 1; // search left to find first valid interval
                } else {
                    low = mid + 1;
                }
            }

            // Calculate starting rank of intervalIndex
            long prevCount = (intervalIndex > 0) ? prefixRank[intervalIndex - 1] : 0;
            long offset = k - prevCount - 1;

            int mark = (int)(l[intervalIndex] + offset);
            result.add(mark);
        }

        return result;
    }
}