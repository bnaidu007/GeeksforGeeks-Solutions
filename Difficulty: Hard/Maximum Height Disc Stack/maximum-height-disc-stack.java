import java.util.Arrays;

class Solution {
    // Binary Indexed Tree (Fenwick Tree) to maintain maximum stack height
    private static class BIT {
        private final int[] tree;
        private final int size;

        public BIT(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public void update(int idx, int val) {
            for (; idx <= size; idx += idx & -idx) {
                tree[idx] = Math.max(tree[idx], val);
            }
        }

        public int query(int idx) {
            int maxVal = 0;
            for (; idx > 0; idx -= idx & -idx) {
                maxVal = Math.max(maxVal, tree[idx]);
            }
            return maxVal;
        }
    }

    public int maxStackHeight(int[] r, int[] h) {
        int n = r.length;
        int[][] discs = new int[n][2];
        int maxHeightVal = 0;

        for (int i = 0; i < n; i++) {
            discs[i][0] = r[i];
            discs[i][1] = h[i];
            maxHeightVal = Math.max(maxHeightVal, h[i]);
        }

        // Sort primarily by radius in ascending order.
        // For equal radii, sort by height in descending order to prevent 
        // selecting discs with identical radii in the same stack.
        Arrays.sort(discs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });

        BIT bit = new BIT(maxHeightVal);
        int totalMaxHeight = 0;

        for (int i = 0; i < n; i++) {
            int height = discs[i][1];

            // Query maximum height possible with discs having strictly smaller height (< height)
            int bestPreviousHeight = bit.query(height - 1);
            int currentStackHeight = bestPreviousHeight + height;

            totalMaxHeight = Math.max(totalMaxHeight, currentStackHeight);

            // Update the Fenwick tree with the new max height at position 'height'
            bit.update(height, currentStackHeight);
        }

        return totalMaxHeight;
    }
}