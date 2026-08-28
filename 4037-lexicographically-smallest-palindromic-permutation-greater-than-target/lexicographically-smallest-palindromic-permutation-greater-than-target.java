class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int middle = -1;
        int odd = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) != 0) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        int[] remaining = halfCnt.clone();
        StringBuilder left = new StringBuilder();

        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                left = null;
                break;
            }

            remaining[c]--;
            left.append((char) ('a' + c));
        }

        if (left != null) {
            String candidate = build(left.toString(), middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        for (int i = half - 1; i >= 0; i--) {
            remaining = halfCnt.clone();

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int cur = target.charAt(i) - 'a';

            for (int c = cur + 1; c < 26; c++) {
                if (remaining[c] == 0) {
                    continue;
                }

                left = new StringBuilder();

                for (int j = 0; j < i; j++) {
                    left.append(target.charAt(j));
                }

                left.append((char) ('a' + c));
                remaining[c]--;

                for (int j = 0; j < 26; j++) {
                    while (remaining[j] > 0) {
                        left.append((char) ('a' + j));
                        remaining[j]--;
                    }
                }

                return build(left.toString(), middle, n);
            }
        }

        return "";
    }

    private String build(String left, int middle, int n) {
        StringBuilder res = new StringBuilder();

        res.append(left);

        if ((n & 1) != 0) {
            res.append((char) ('a' + middle));
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            res.append(left.charAt(i));
        }

        return res.toString();
    }
}