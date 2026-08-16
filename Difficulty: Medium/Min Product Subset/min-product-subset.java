class Solution {
    public int minProd(int[] arr) {
        // code here
     int negativeCount = 0;
             int zeroCount = 0;
             int smallestPositive = Integer.MAX_VALUE;
             int closestNegative = Integer.MIN_VALUE;

             long product = 1;

             for (int x : arr) {
                 if (x < 0) {
                     negativeCount++;
                     closestNegative = Math.max(closestNegative, x);
                 } else if (x == 0) {
                     zeroCount++;
                 } else {
                     smallestPositive = Math.min(smallestPositive, x);
                 }
             }

             // No negative numbers
             if (negativeCount == 0) {
                 if (zeroCount > 0) {
                     return 0;
                 }
                 return smallestPositive;
             }

             // Multiply all non-zero elements
             for (int x : arr) {
                 if (x != 0) {
                     product *= x;
                 }
             }

             // If number of negatives is even,
             // remove the negative closest to zero
             if (negativeCount % 2 == 0) {
                 product /= closestNegative;
             }

             return (int) product;
         }
       
    
}