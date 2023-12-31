import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {
    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        root = add(root, data);
    }

    private AVLNode<T> add(AVLNode<T> node, T data) {
        if (node == null) {
            size++;
            node = new AVLNode<>(data);
        } else {
            var result = data.compareTo(node.getData());
            if (result > 0) {
                // data is larger, traverse right
                var rightNode = add(node.getRight(), data);
                node.setRight(rightNode);
            }
            if (result < 0) {
                // data is smaller, traverse left
                var leftNode = add(node.getLeft(), data);
                node.setLeft(leftNode);
            }
        }

        node = balance(node);
        return node;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        var removedNode = new AVLNode<T>(null);
        root = removeNode(root, data, removedNode);
        if (root != null) {
            root = balance(root);
        }

        if (removedNode.getData() == null) {
            throw new NoSuchElementException();
        }

        return removedNode.getData();
    }

    private AVLNode<T> removeNode(AVLNode<T> node, T data, AVLNode<T> removedNode) {
        if (node == null) {
            // no data found
            return null;
        }

        var result = data.compareTo(node.getData());
        if (result > 0) {
            // data is larger than node, traverse right
            var rightNode = removeNode(node.getRight(), data, removedNode);
            if (removedNode.getData() != null) {
                rightNode = balance(rightNode);
            }
            node.setRight(rightNode);
        }
        if (result < 0) {
            // data is smaller than node, traverse left
            var leftNode = removeNode(node.getLeft(), data, removedNode);
            if (removedNode.getData() != null) {
                leftNode = balance(leftNode);
            }
            node.setLeft(leftNode);
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
            // * 2: The node containing the data has one child. In this case, simply
            // * replace it with its child.

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
            // As a reminder, rotations can occur
            // * after removing the successor node.
        }

        return node;
    }

    private AVLNode<T> successionWrapper(AVLNode<T> node) {
        var successionNode = new AVLNode<T>(null);
        var rightNode = succession(node.getRight(), successionNode);
        if (rightNode != null) {
            rightNode = balance(rightNode);
        }
        node.setRight(rightNode);
        node.setData(successionNode.getData());
        return node;
    }

    private AVLNode<T> succession(AVLNode<T> node, AVLNode<T> successionNode) {
        if (node.getLeft() == null) {
            successionNode.setData(node.getData());
            return node.getRight();
        }
        var returnNode = succession(node.getLeft(), successionNode);
        if (returnNode != null) {
            returnNode = balance(returnNode);
        }
        node.setLeft(returnNode);
        return node;
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param node The node to update the height and balance factor of.
     */
    public void updateHeightAndBF(AVLNode<T> node) {
        var leftChild = node.getLeft();
        var leftChildHeight = leftChild == null ? -1 : leftChild.getHeight();

        var rightChild = node.getRight();
        var rightChildHeight = rightChild == null ? -1 : rightChild.getHeight();

        var maxHeight = Math.max(leftChildHeight, rightChildHeight);
        node.setHeight(maxHeight + 1);

        node.setBalanceFactor(leftChildHeight - rightChildHeight);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        var newParent = currentNode.getRight();
        currentNode.setRight(currentNode.getRight().getLeft());
        newParent.setLeft(currentNode);

        updateHeightAndBF(currentNode);
        updateHeightAndBF(newParent);

        return newParent;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        var newParent = currentNode.getLeft();
        currentNode.setLeft(currentNode.getLeft().getRight());
        newParent.setRight(currentNode);

        updateHeightAndBF(currentNode);
        updateHeightAndBF(newParent);

        return newParent;
    }

    /**
     * Method that balances out the tree starting at the node passed in.
     * This method should be called in your add() and remove() methods to
     * facilitate rebalancing your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        if (currentNode == null) {
            return null;
        }

        updateHeightAndBF(currentNode);

        // if right heavy, left rotation needed
        if (currentNode.getBalanceFactor() < -1) {
            if (currentNode.getRight().getBalanceFactor() > 0) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if (currentNode.getBalanceFactor() > 1) {
            if (currentNode.getLeft().getBalanceFactor() < 0) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
