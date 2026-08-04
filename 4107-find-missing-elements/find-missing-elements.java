class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        ArrayList<Integer>list=new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        int s=nums[0];    
        for(int i=0;i<n;i++)
        {
            while(s<nums[i])
            {
                list.add(s);
                s++;
            }
            s=nums[i]+1;
        }
        return list;  
    }
}