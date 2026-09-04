class Solution {
    public int firstStableIndex(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<nums.length;i++)
        {
            list.add(nums[i]);
        }
        int c=0;
        int maxSoFar = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            maxSoFar = Math.max(maxSoFar,nums[i]);
            int min = Collections.min(list);
            c=maxSoFar-min;
            if(c<=k)
            {
                return i;
            }
            list.removeFirst();
        }
        return -1;

        
    }
}