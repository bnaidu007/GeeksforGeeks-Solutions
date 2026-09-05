import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestSubseq(int[] arr) {
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLength = 0;

        for (int num : arr) {
            int left = dp.getOrDefault(num - 1, 0);
            int right = dp.getOrDefault(num + 1, 0);

            int currentLength = Math.max(left, right) + 1;
            dp.put(num, currentLength);

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}