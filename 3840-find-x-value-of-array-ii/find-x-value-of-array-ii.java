class Solution {
    static class Node {
        int prod;
        int[] pref;

        Node(int k) {
            pref = new int[k];
        }
    }

    int n, k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int index = queries[qi][0];
            int value = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[qi] = res.pref[x];
        }

        return ans;
    }

    private void build(int node, int l, int r, int[] nums) {
        tree[node] = new Node(k);

        if (l == r) {
            int rem = nums[l] % k;
            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            int rem = value % k;

            tree[node] = new Node(k);
            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node(k);

        res.prod = (left.prod * right.prod) % k;

        for (int rem = 0; rem < k; rem++) {
            res.pref[rem] += left.pref[rem];
        }

        for (int rightRem = 0; rightRem < k; rightRem++) {
            int count = right.pref[rightRem];

            if (count == 0) {
                continue;
            }

            int combined = (left.prod * rightRem) % k;
            res.pref[combined] += count;
        }

        return res;
    }
}
