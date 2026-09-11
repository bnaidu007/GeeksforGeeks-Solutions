class Solution {
    public int sameMod(int[] arr) {
        int n = arr.length;

        // Find the greatest common divisor (GCD) of differences with arr[0]
        int g = 0;
        for (int i = 1; i < n; i++) {
            g = gcd(g, Math.abs(arr[i] - arr[0]));
        }

        // If all elements are equal, differences are 0, g remains 0 -> infinite k values
        if (g == 0) {
            return -1;
        }

        // Count total divisors of g
        int count = 0;
        for (int i = 1; i * i <= g; i++) {
            if (g % i == 0) {
                count++; // i is a divisor
                if (i * i != g) {
                    count++; // g / i is also a distinct divisor
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