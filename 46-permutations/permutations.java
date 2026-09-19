class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());

        for (int num : nums) {
            int n = outer.size();
            List<List<Integer>> newOuter = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                List<Integer> current = outer.get(i);

                for (int j = 0; j <= current.size(); j++) {
                    List<Integer> internal = new ArrayList<>(current);
                    internal.add(j, num);
                    newOuter.add(internal);
                }
            }

            outer = newOuter;
        }

        return outer;
    }
}
