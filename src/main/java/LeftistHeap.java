public class LeftistHeap {
    static class Node {
        private final int key;
        private int rank;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
            this.rank = 1;
        }

        public int getRank() { return rank; }
        public Node getLeft() { return left; }
        public Node getRight() { return right; }
    }

    private Node root;

    public LeftistHeap() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int key) {
        LeftistHeap tempHeap = new LeftistHeap();
        tempHeap.root = new Node(key);
        root = merge(root, tempHeap.root);
    }

    public int deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = root.key;
        root = merge(root.left, root.right);
        return min;
    }

    public void merge(LeftistHeap other) {
        this.root = merge(this.root, other.root);
        other.root = null;
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.key > h2.key) {
            Node temp = h1;
            h1 = h2;
            h2 = temp;
        }

        h1.right = merge(h1.right, h2);

        if (h1.left == null || h1.left.rank < h1.right.rank) {
            Node temp = h1.left;
            h1.left = h1.right;
            h1.right = temp;
        }

        h1.rank = (h1.right == null) ? 1 : h1.right.rank + 1;
        return h1;
    }

    Node getRoot() {
        return root;
    }
}
