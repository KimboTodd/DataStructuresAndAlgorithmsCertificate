import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public List<T> order() {
        List<T> expected = new java.util.ArrayList<>();

        order(expected, this.root);

        return expected;
    }

    private void order(List<T> expected, BSTNode<T> node) {
        if (node != null) {
            order(expected, node.getLeft());

            expected.add(node.getData());

            order(expected, node.getRight());
        }
    }

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        root = addData(root, data);
    }

    private BSTNode<T> addData(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }

        var result = data.compareTo(node.getData());
        if (result > 0) {
            var rightData = addData(node.getRight(), data);
            node.setRight(rightData);
        } else if (result < 0) {
            var leftData = addData(node.getLeft(), data);
            node.setLeft(leftData);
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        BSTNode<T> removedNode = new BSTNode<>(null);

        var result = removeData(root, data, removedNode);
        root = result;
        if (removedNode.getData() == null) {
            throw new NoSuchElementException();
        }

        return removedNode.getData();
    }

    private BSTNode<T> removeData(BSTNode<T> node, T data, BSTNode<T> removedNode) {
        if (node == null) {
            return null;
        }
        var result = data.compareTo(node.getData());
        if (result > 0) {
            var rightData = removeData(node.getRight(), data, removedNode);
            node.setRight(rightData);
        }
        if (result < 0) {
            var leftData = removeData(node.getLeft(), data, removedNode);
            node.setLeft(leftData);
        }
        if (result == 0) {
            // data found
            var left = node.getLeft();
            var right = node.getRight();

            if (left == null && right == null) {
                // if there are no children, remove the node
                removedNode.setData(node.getData());
                size--;
                return null;
            }
            if (left != null && right == null) {
                // there is one child
                removedNode.setData(node.getData());
                size--;
                return left;
            }

            if (right != null && left == null) {
                // there is one child
                removedNode.setData(node.getData());
                size--;
                return right;
            }

            if (left != null && right != null) {
                removedNode.setData(node.getData());
                size--;
                return successionWrapper(node);
            }
        }

        return node;
    }

    private BSTNode<T> successionWrapper(BSTNode<T> node) {
        BSTNode<T> successionNode = new BSTNode<>(null);
        var rightNode = succession(node.getRight(), successionNode);
        node.setRight(rightNode);
        node.setData(successionNode.getData());
        return node;
    }

    private BSTNode<T> succession(BSTNode<T> node, BSTNode<T> successionNode) {
        if (node.getLeft() == null) {
            successionNode.setData(node.getData());
            return node.getRight();
        }
        var returnNode = succession(node.getLeft(), successionNode);
        node.setLeft(returnNode);
        return node;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

}