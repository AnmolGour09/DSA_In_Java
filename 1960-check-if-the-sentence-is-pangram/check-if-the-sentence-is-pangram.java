class Solution {
    public boolean checkIfPangram(String s) {
        boolean[] a=new boolean[26];
        for(int i=0;i<s.length();i++)
        {
            int l=s.charAt(i)-'a';
            a[l]=true;
        }  
        for(int i=0;i<26;i++)
        {
            if(a[i]==false)
            {
                return false;
            }
        }
        return true;
    }
}
