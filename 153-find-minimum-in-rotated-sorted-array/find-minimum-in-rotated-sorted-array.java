class Solution {
    public int findMin(int[] a) {

        int p = pivot(a);


        if( p>=0 &&a[p]>a[p+1] )
        {
            return a[p+1];
        }
        else{
            return a[0];
        }

    }

    public static int pivot(int[] a) {
        int n = a.length;
        int s = 0;
        int e = n - 1;

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (mid < e && a[mid] > a[mid + 1]) {
                return mid;
            }

            if (mid > s && a[mid] < a[mid - 1]) {
                return mid - 1;
            }

            if (a[mid] > a[e]) {
                // Pivot is on the right
                s = mid + 1;

            } else {
                // Pivot is on the left
                e = mid - 1;
            }
        }

        return -1;
    }

    
}
