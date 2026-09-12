class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
            return Integer.compare(x[3], y[3]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid][0] > a[i][1]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] choice = new int[n + 1][5][];

        for (int k = 0; k <= 4; k++) {
            choice[n][k] = new int[0];
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                choice[i][k] = choice[i + 1][k];

                if (k > 0) {
                    long takeScore = (long) a[i][2] + dp[next[i]][k - 1];

                    int[] takeChoice = addAndSort(
                        a[i][3],
                        choice[next[i]][k - 1]
                    );

                    if (takeScore > dp[i][k] ||
                        (takeScore == dp[i][k] &&
                         lexicographicallySmaller(takeChoice, choice[i][k]))) {
                        dp[i][k] = takeScore;
                        choice[i][k] = takeChoice;
                    }
                }
            }
        }

        return choice[0][4];
    }

    private int[] addAndSort(int index, int[] arr) {
        int[] res = new int[arr.length + 1];

        int p = 0;

        while (p < arr.length && arr[p] < index) {
            res[p] = arr[p];
            p++;
        }

        res[p] = index;

        while (p < arr.length) {
            res[p + 1] = arr[p];
            p++;
        }

        return res;
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}
