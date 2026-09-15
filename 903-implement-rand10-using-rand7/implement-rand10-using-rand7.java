/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while(true)
        {
            int a=rand7();
            int b=rand7();
            int n=(a-1)*7+b;
            if(n<=40){
                return 1+(n-1)%10;
            }
        }
    }
}