class Solution {
    public int findMax(int n) {
        String s = String.valueOf(n);
        char[] digits = s.toCharArray();

        int bestNum = n;
        int maxDigitSum = getDigitSum(n);

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '0') continue;

            // Create candidate by reducing digit at index i and replacing rest with '9'
            char[] temp = digits.clone();
            temp[i]--;
            for (int j = i + 1; j < temp.length; j++) {
                temp[j] = '9';
            }

            int candidate = Integer.parseInt(new String(temp));
            int currentSum = getDigitSum(candidate);

            // Update if strictly greater, or equal with a larger number
            if (currentSum > maxDigitSum || (currentSum == maxDigitSum && candidate > bestNum)) {
                maxDigitSum = currentSum;
                bestNum = candidate;
            }
        }

        return bestNum;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}