import java.util.ArrayList;

 class Solution {
     public boolean isPossible(int[] arr, int s, int x) {
         if (x == 0) return true;

         ArrayList<Long> seq = new ArrayList<>();
         seq.add((long) s);

         long runningSum = s;

         // Generate sequence numbers up to x
         for (int i = 0; i < arr.length; i++) {
             long nextVal = runningSum + arr[i];
             if (nextVal > x) {
                 break;
             }
             seq.add(nextVal);
             runningSum += nextVal;
         }

         // Greedy choice from largest to smallest
         for (int i = seq.size() - 1; i >= 0; i--) {
             if (x >= seq.get(i)) {
                 x -= seq.get(i);
             }
             if (x == 0) {
                 return true;
             }
         }

         return x == 0;
     }
 }