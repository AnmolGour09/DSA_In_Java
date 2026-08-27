class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';

            if (freq[t] > 0) {
                freq[t]--;
                prefix.append((char) ('a' + t));
                continue;
            }

            for (int c = t + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    StringBuilder ans = new StringBuilder(prefix);
                    ans.append((char) ('a' + c));
                    freq[c]--;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x]-- > 0) {
                            ans.append((char) ('a' + x));
                        }
                    }

                    return ans.toString();
                }
            }

            break;
        }

        for (int i = prefix.length() - 1; i >= 0; i--) {
            int old = prefix.charAt(i) - 'a';
            freq[old]++;

            for (int c = old + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    StringBuilder ans = new StringBuilder();
                    ans.append(prefix, 0, i);
                    ans.append((char) ('a' + c));
                    freq[c]--;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x]-- > 0) {
                            ans.append((char) ('a' + x));
                        }
                    }

                    return ans.toString();
                }
            }

            prefix.deleteCharAt(i);
        }

        return "";
    }
}