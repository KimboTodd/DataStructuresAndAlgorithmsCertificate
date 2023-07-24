import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;

public class AVLTest {

    @Test
    public void testUpdateHeightAndBFWithSingleNode() {
        AVL<Integer> tree = new AVL<>();
        AVLNode<Integer> node = new AVLNode<>(1);
        tree.updateHeightAndBF(node);
        Assert.assertEquals(0, node.getBalanceFactor());
        Assert.assertEquals(0, node.getHeight());
    }

    @Test
    public void testUpdateHeightAndBFWithLeftChild() {
        AVL<Integer> tree = new AVL<>();
        AVLNode<Integer> node = new AVLNode<>(2);
        AVLNode<Integer> left = new AVLNode<>(1);
        node.setLeft(left);
        tree.updateHeightAndBF(node);
        Assert.assertEquals(1, node.getBalanceFactor());
        Assert.assertEquals(1, node.getHeight());
    }

    @Test
    public void testUpdateHeightAndBFWithRightChild() {
        AVL<Integer> tree = new AVL<>();
        AVLNode<Integer> node = new AVLNode<>(1);
        AVLNode<Integer> right = new AVLNode<>(2);
        node.setRight(right);
        tree.updateHeightAndBF(node);
        Assert.assertEquals(-1, node.getBalanceFactor());
        Assert.assertEquals(1, node.getHeight());
    }

    @Test
    public void testAddSingleNode() {
        AVL<Integer> tree = new AVL<>();
        tree.add(1);
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals(0, tree.getRoot().getHeight());
        Assert.assertEquals(0, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(1, tree.getRoot().getData().intValue());
    }

    @Test
    public void testAddLeftLeftCase() {
        AVL<Integer> tree = new AVL<>();
        tree.add(3);
        tree.add(2);
        tree.add(1);
        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(0, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(2, tree.getRoot().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getLeft().getData().intValue());
        Assert.assertEquals(3, tree.getRoot().getRight().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getHeight());
        Assert.assertEquals(0, tree.getRoot().getLeft().getHeight());
        Assert.assertEquals(0, tree.getRoot().getRight().getHeight());
    }

    @Test
    public void testAddDuplicateValue() {
        AVL<Integer> tree = new AVL<>();
        tree.add(1);
        tree.add(2);
        tree.add(1);
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(-1, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(1, tree.getRoot().getData().intValue());
        Assert.assertNull(tree.getRoot().getLeft());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNonexistentNode() {
        AVL<Integer> tree = new AVL<>();
        tree.add(1);
        tree.add(2);
        tree.remove(3);
    }

    @Test
    public void testRemoveNonexistentNode2() {
        AVL<Integer> tree = new AVL<>();
        tree.add(1);
        tree.add(2);
        tree.remove(3);
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(0, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(2, tree.getRoot().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getLeft().getData().intValue());
        Assert.assertNull(tree.getRoot().getRight());
    }

    @Test
    public void testRemoveSingleNode() {
        AVL<Integer> tree = new AVL<>();
        tree.add(1);
        tree.remove(1);
        Assert.assertEquals(0, tree.size());
        Assert.assertNull(tree.getRoot());
    }

    @Test
    public void testRemoveLeafNode() {
        AVL<Integer> tree = new AVL<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.remove(1);
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(1, tree.getRoot().getData().intValue());
        Assert.assertEquals(0, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(3, tree.getRoot().getRight().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getHeight());
        Assert.assertEquals(1, tree.getRoot().getRight().getHeight());
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        AVL<Integer> tree = new AVL<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.remove(3);
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(1, tree.getRoot().getData().intValue());
        Assert.assertEquals(0, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(2, tree.getRoot().getRight().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getHeight());
        Assert.assertEquals(1, tree.getRoot().getRight().getHeight());
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        AVL<Integer> tree = new AVL<>();
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.remove(2);
        Assert.assertEquals(6, tree.size());
        Assert.assertEquals(4, tree.getRoot().getData().intValue());
        Assert.assertEquals(1, tree.getRoot().getBalanceFactor());
        Assert.assertEquals(1, tree.getRoot().getLeft().getData().intValue());
        Assert.assertEquals(6, tree.getRoot().getRight().getData().intValue());
        Assert.assertEquals(2, tree.getRoot().getHeight());
        Assert.assertEquals(1, tree.getRoot().getLeft().getHeight());
        Assert.assertEquals(1, tree.getRoot().getRight().getHeight());
    }

    @Test
    public void testRemoveNodeWithTwoChildren2() {
        AVL<Integer> tree = new AVL<>();
        tree.add(7);
        tree.add(4);
        tree.add(10);
        tree.add(2);
        tree.add(6);
        tree.add(8);
        tree.add(11);
        tree.add(0);
        tree.add(3);
        tree.add(5);
        tree.add(9);
        tree.add(1);
        tree.remove(4);
        // expected tree

        AVLNode<Integer> expectedRoot = new AVLNode<>(7);
        expectedRoot.setLeft(new AVLNode<>(2));
        expectedRoot.setRight(new AVLNode<>(10));
        expectedRoot.getLeft().setLeft(new AVLNode<>(0));
        expectedRoot.getLeft().setRight(new AVLNode<>(5));
        expectedRoot.getLeft().getRight().setLeft(new AVLNode<>(3));
        expectedRoot.getLeft().getRight().setRight(new AVLNode<>(6));
        expectedRoot.getLeft().getLeft().setRight(new AVLNode<>(1));
        expectedRoot.getRight().setLeft(new AVLNode<>(8));
        expectedRoot.getRight().setRight(new AVLNode<>(11));
        expectedRoot.getRight().getLeft().setRight(new AVLNode<>(9));
        Assert.assertEquals(expectedRoot.getData(), tree.getRoot().getData());
        Assert.assertEquals(expectedRoot.getLeft().getData(), tree.getRoot().getLeft().getData());
        Assert.assertEquals(expectedRoot.getRight().getData(), tree.getRoot().getRight().getData());
        Assert.assertEquals(expectedRoot.getLeft().getLeft().getData(), tree.getRoot().getLeft().getLeft().getData());
        Assert.assertEquals(expectedRoot.getLeft().getRight().getData(), tree.getRoot().getLeft().getRight().getData());
        Assert.assertEquals(expectedRoot.getLeft().getRight().getLeft().getData(),
                tree.getRoot().getLeft().getRight().getLeft().getData());
        Assert.assertEquals(expectedRoot.getLeft().getRight().getRight().getData(),
                tree.getRoot().getLeft().getRight().getRight().getData());
        Assert.assertEquals(expectedRoot.getLeft().getLeft().getRight().getData(),
                tree.getRoot().getLeft().getLeft().getRight().getData());
        Assert.assertEquals(expectedRoot.getRight().getLeft().getData(), tree.getRoot().getRight().getLeft().getData());
        Assert.assertEquals(expectedRoot.getRight().getRight().getData(),
                tree.getRoot().getRight().getRight().getData());
        Assert.assertEquals(expectedRoot.getRight().getLeft().getRight().getData(),
                tree.getRoot().getRight().getLeft().getRight().getData());
    }

    // [Test Failure: remove] [-0.23] : Unexpected content after removing 4 from the
    // tree.

    // +-----------------------------------------------------------------+
    // | Initial Tree: |
    // | |
    // | 7 |
    // | / \ |
    // | 4 10 |
    // | / \ / \ |
    // | 2 6 8 11 |
    // | / \ / \ |
    // | 0 3 5 9 |
    // | \ |
    // | 1 |
    // | |
    // | |
    // | Expected Tree: remove 4 |
    // | |
    // | 7 |
    // | / \ |
    // | 2 10 |
    // | / \ / \ |
    // | 0 5 8 11 |
    // | \ / \ \ |
    // | 1 3 6 9 |
    // | |
    // | |
    // | Actual Tree: |
    // | |
    // | 7 |
    // | / \ |
    // | 5 10 |
    // | / \ / \ |
    // | 2 6 8 11 |
    // | / \ \ |
    // | 0 3 9 |
    // | \ |
    // | 1 |
    // +-----------------------------------------------------------------+
}
