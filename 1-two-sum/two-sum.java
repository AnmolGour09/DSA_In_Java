class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> index=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            index.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++)
        {
            int diff=target-nums[i];
            if(index.containsKey(diff)&& index.get(diff)!=i)
            {
                return new int[] {i, index.get(diff)};
            }
        }

        return new int[0];
    }
}