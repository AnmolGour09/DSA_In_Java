class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] right = new int[m];
        int p = n - 1;

        for (int j = m - 1; j >= 0; j--) {

            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }

            if (p < 0) {
                right[j] = -1;
            } else {
                right[j] = p;
                p--;
            }
        }

        int[] ans = new int[m];

        int j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {

            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            else if (!changed) {

               
                if (j == m - 1 || right[j + 1] > i) {

                    ans[j] = i;
                    j++;
                    changed = true;
                }
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}