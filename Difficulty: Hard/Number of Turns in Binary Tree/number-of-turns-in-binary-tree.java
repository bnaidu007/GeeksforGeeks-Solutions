class Solution {
    private Node findLCA(Node root, int p, int q) {
        if (root == null || root.data == p || root.data == q) {
            return root;
        }

        Node left = findLCA(root.left, p, q);
        Node right = findLCA(root.right, p, q);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    private boolean countTurns(Node node, int target, char currentDir, int[] turns) {
        if (node == null) return false;
        if (node.data == target) return true;

        // Traverse left
        if (countTurns(node.left, target, 'L', turns)) {
            if (currentDir != 'L') turns[0]++;
            return true;
        }

        // Traverse right
        if (countTurns(node.right, target, 'R', turns)) {
            if (currentDir != 'R') turns[0]++;
            return true;
        }

        return false;
    }

    public int numberOfTurns(Node root, int p, int q) {
        Node lca = findLCA(root, p, q);
        if (lca == null) return -1;

        int[] turns = new int[1];

        // Case 1: p and q are in different subtrees of LCA
        if (lca.data != p && lca.data != q) {
            countTurns(lca.left, p, 'L', turns);
            countTurns(lca.right, p, 'R', turns);
            countTurns(lca.left, q, 'L', turns);
            countTurns(lca.right, q, 'R', turns);

            // 1 turn occurs at the LCA when transitioning from p's path to q's path
            return turns[0] + 1;
        }

        // Case 2: One node is an ancestor of the other
        int target = (lca.data == p) ? q : p;

        // Try going left or right from the LCA towards target
        countTurns(lca.left, target, 'L', turns);
        countTurns(lca.right, target, 'R', turns);

        return turns[0] == 0 ? -1 : turns[0];
    }
}