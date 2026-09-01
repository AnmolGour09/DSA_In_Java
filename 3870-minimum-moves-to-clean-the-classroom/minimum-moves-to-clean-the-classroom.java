import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;
        List<int[]> litter = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litter.add(new int[]{r, c});
                }
            }
        }

        int k = litter.size();

        if (k == 0) return 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < k; i++) {
            int r = litter.get(i)[0];
            int c = litter.get(i)[1];
            litterId[r][c] = i;
        }

        int fullMask = (1 << k) - 1;

        int[][][] best = new int[m][n][1 << k];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(best[r][c], -1);
            }
        }

        class State {
            int r, c, mask, energy;

            State(int r, int c, int mask, int energy) {
                this.r = r;
                this.c = c;
                this.mask = mask;
                this.energy = energy;
            }
        }

        Queue<State> queue = new ArrayDeque<>();

        best[sr][sc][0] = energy;
        queue.offer(new State(sr, sc, 0, energy));

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                State cur = queue.poll();

                if (cur.mask == fullMask) {
                    return moves;
                }

                if (cur.energy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = cur.energy - 1;
                    int newMask = cur.mask;

                    char next = classroom[nr].charAt(nc);

                    if (next == 'L') {
                        newMask |= 1 << litterId[nr][nc];
                    }

                    if (next == 'R') {
                        newEnergy = energy;
                    }

                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;
                    queue.offer(new State(nr, nc, newMask, newEnergy));
                }
            }

            moves++;
        }

        return -1;
    }
}
