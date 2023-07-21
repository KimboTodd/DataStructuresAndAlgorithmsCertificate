import java.util.List;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     * CLR
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        List<T> expected = new java.util.ArrayList<>();

        preorder(expected, root);

        return expected;
    }

    private void preorder(List<T> expected, TreeNode<T> node) {
        if (node != null) {
            expected.add(node.getData());

            preorder(expected, node.getLeft());

            preorder(expected, node.getRight());
        }
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     * 
     * LCR
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        List<T> expected = new java.util.ArrayList<>();

        inorder(expected, root);

        return expected;
    }

    private void inorder(List<T> expected, TreeNode<T> node) {
        if (node != null) {
            inorder(expected, node.getLeft());

            expected.add(node.getData());

            inorder(expected, node.getRight());
        }
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     * 
     * LRC
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        List<T> expected = new java.util.ArrayList<>();

        postorder(expected, root);

        return expected;
    }

    private void postorder(List<T> expected, TreeNode<T> node) {
        if (node != null) {
            postorder(expected, node.getLeft());

            postorder(expected, node.getRight());

            expected.add(node.getData());
        }
    }

}