import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rows = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            if (s >= 2 && s <= 9) {
                int bit = 1 << (s - 2);
                rows.put(row, rows.getOrDefault(row, 0) | bit);
            }
        }

        long result = (long) (n - rows.size()) * 2;

        for (int mask : rows.values()) {
            boolean left = (mask & 0b00001111) == 0;  
            boolean middle = (mask & 0b00111100) == 0;
            boolean right = (mask & 0b11110000) == 0;  

            if (left && right) {
                result += 2;
            } else if (left || middle || right) {
                result += 1;
            }
        }

        return (int) result;
    }
}