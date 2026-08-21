class Solution {

    int time = 0;

    public List<List<Integer>> criticalConnections(
            int n,
            List<List<Integer>> connections) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] disc = new int[n];
        int[] low = new int[n];

        Arrays.fill(disc, -1);

        List<List<Integer>> result = new ArrayList<>();

        dfs(0, -1, graph, disc, low, result);

        return result;
    }

    private void dfs(
            int current,
            int parent,
            List<List<Integer>> graph,
            int[] disc,
            int[] low,
            List<List<Integer>> result) {

        disc[current] = low[current] = time++;

        for (int next : graph.get(current)) {

            if (next == parent) {
                continue;
            }

            if (disc[next] == -1) {

                dfs(next, current, graph, disc, low, result);

                low[current] = Math.min(low[current], low[next]);

                if (low[next] > disc[current]) {
                    result.add(Arrays.asList(current, next));
                }

            } else {
                low[current] = Math.min(low[current], disc[next]);
            }
        }
    }
}