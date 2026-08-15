class Solution {
    public int countWithout(int n, int d) {
        if (n <= 0) {
            return 0;
        }

        String s = String.valueOf(n);
        int len = s.length();

        int[] pow9 = new int[len + 1];
        pow9[0] = 1;

        for (int i = 1; i <= len; i++) {
            pow9[i] = pow9[i - 1] * 9;
        }

        int count = 0;

        // Special case when d = 0
        if (d == 0) {

            // Count numbers having fewer digits than n
            for (int digits = 1; digits < len; digits++) {
                count += pow9[digits];
            }

            // Count numbers having same number of digits as n
            for (int i = 0; i < len; i++) {
                int digit = s.charAt(i) - '0';
                int remaining = len - i - 1;

                // First digit cannot be 0
                if (i == 0) {
                    count += (digit - 1) * pow9[remaining];
                } else {
                    int choices = 0;

                    for (int c = 0; c < digit; c++) {
                        if (c != 0) {
                            choices++;
                        }
                    }

                    count += choices * pow9[remaining];
                }

                // n contains 0, so n itself is not valid
                if (digit == 0) {
                    return count;
                }
            }

            // n itself does not contain 0
            return count + 1;
        }

        // d != 0
        boolean hitD = false;

        for (int i = 0; i < len; i++) {
            int digit = s.charAt(i) - '0';
            int remaining = len - 1 - i;

            int choices = 0;

            for (int c = 0; c < digit; c++) {
                if (c != d) {
                    choices++;
                }
            }

            count += choices * pow9[remaining];

            if (digit == d) {
                hitD = true;
                break;
            }
        }

        // Include n if it does not contain d
        if (!hitD) {
            count++;
        }

        // For d != 0, zero was counted, so remove it
        return count - 1;
    }
}