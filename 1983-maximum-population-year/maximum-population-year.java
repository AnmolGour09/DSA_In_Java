class Solution {
    public int maximumPopulation(int[][] logs) {
        int n=logs.length;

        int maxPopulation = 0;
        int answer = 1950;
        
        // here we find the min and max in the 2d array logs
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;

        for (int[] log : logs) {
            minYear = Math.min(minYear, log[0]);//├── logs[0] → [1950, 1961]
            maxYear = Math.max(maxYear, log[1]);//├── logs[1] → [1960, 1971]
        }

        for (int year = minYear; year < maxYear; year++) {

            int count = 0;

            for (int i = 0; i < logs.length; i++) {

                if (year >= logs[i][0] && year < logs[i][1]) {
                    count++;
                }
            }

            if (count > maxPopulation) {
                maxPopulation = count;
                answer = year;
            }
        }

        return answer;
    }
}