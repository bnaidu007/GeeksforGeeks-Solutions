class Solution {
    public int countSubsequences(String s, int n) {
        long MOD = 1000000007;
        long[] dp = new long[n];

        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            long[] next_dp = dp.clone();

            // Start a new single-digit subsequence with 'digit'
            next_dp[digit % n] = (next_dp[digit % n] + 1) % MOD;

            // Append 'digit' to existing subsequences
            for (int r = 0; r < n; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * 10 + digit) % n;
                    next_dp[newRem] = (next_dp[newRem] + dp[r]) % MOD;
                }
            }

            dp = next_dp;
        }

        return (int) dp[0];
    }
}