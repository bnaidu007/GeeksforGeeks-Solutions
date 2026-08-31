class Solution {
    public int findMaxAverage(List<Integer> arr, int k) {
        // code here
        int sum = 0;
        for(int i=0;i<k;i++){
          sum+=arr.get(i); 
        }
        int index = 0;
        int winsum = sum;
        for(int i=k;i<arr.size();i++){
            winsum +=arr.get(i)-arr.get(i-k);
            
            if(winsum>sum){
                sum = winsum;
                index = i-k+1;
            }
        }
        return index;
        
    }
}