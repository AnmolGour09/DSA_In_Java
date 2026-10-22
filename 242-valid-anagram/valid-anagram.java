class Solution {
    public boolean isAnagram(String s, String t) {
        int[] count=new int[26];
        if(s.length()!=t.length())
        {
            return false;
        }
        else{
            for(int i=0;i<s.length();i++)
            {
                count[s.charAt(i)-'a']++;
                count[t.charAt(i)-'a']--;
            }
        }
        for(int num: count)
        {
            if(num!=0)
            {
                return false;
            }
        }
        return true;
        
    }
}