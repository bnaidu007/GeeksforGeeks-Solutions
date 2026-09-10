class Solution {
    public int pairCount(int x, int y) {
        // LCM must be divisible by GCD
        if (y % x != 0) {
            return 0;
        }

        int k = y / x;
        int count = 0;

        for (int p = 1; p * p <= k; p++) {
            if (k % p == 0) {
                int q = k / p;

                // Check if p and q are coprime
                if (gcd(p, q) == 1) {
                    if (p == q) {
                        count += 1;
                    } else {
                        count += 2; // (p, q) and (q, p)
                    }
                }
            }
        }

        return count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}