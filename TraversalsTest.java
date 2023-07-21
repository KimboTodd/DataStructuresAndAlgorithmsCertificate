import org.junit.Before;
import org.junit.Test;

import java.util.List;

import org.junit.Assert;

public class TraversalsTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testPreorder() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(1));
        root.getLeft().setRight(new TreeNode<>(3));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(7));

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> preorder = traversals.preorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(4);
        expected.add(2);
        expected.add(1);
        expected.add(3);
        expected.add(6);
        expected.add(5);
        expected.add(7);

        Assert.assertEquals(expected, preorder);
    }

    @Test
    public void testPreorderWithSingleNode() {
        TreeNode<Integer> root = new TreeNode<>(1);

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> preorder = traversals.preorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);

        Assert.assertEquals(expected, preorder);
    }

    @Test
    public void testPreorderWithEmptyTree() {
        TreeNode<Integer> root = null;

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> preorder = traversals.preorder(root);

        List<Integer> expected = new java.util.ArrayList<>();

        Assert.assertEquals(expected, preorder);
    }

    @Test
    public void testPreorderWithLeftSkewedTree() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().getLeft().setLeft(new TreeNode<>(4));

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> preorder = traversals.preorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        Assert.assertEquals(expected, preorder);
    }

    @Test
    public void testPreorderWithRightSkewedTree() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.setRight(new TreeNode<>(2));
        root.getRight().setRight(new TreeNode<>(3));
        root.getRight().getRight().setRight(new TreeNode<>(4));

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> preorder = traversals.preorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);

        Assert.assertEquals(expected, preorder);
    }

    @Test
    public void testInorder() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(1));
        root.getLeft().setRight(new TreeNode<>(3));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(7));

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> inorder = traversals.inorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, inorder);
    }

    @Test
    public void testInorderWithSingleNode() {
        TreeNode<Integer> root = new TreeNode<>(1);

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> inorder = traversals.inorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);

        Assert.assertEquals(expected, inorder);
    }

    @Test
    public void testInorderWithEmptyTree() {
        TreeNode<Integer> root = null;

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> inorder = traversals.inorder(root);

        List<Integer> expected = new java.util.ArrayList<>();

        Assert.assertEquals(expected, inorder);
    }

    @Test
    public void testPostorder() {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(6));
        root.getLeft().setLeft(new TreeNode<>(1));
        root.getLeft().setRight(new TreeNode<>(3));
        root.getRight().setLeft(new TreeNode<>(5));
        root.getRight().setRight(new TreeNode<>(7));

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> postorder = traversals.postorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(2);
        expected.add(5);
        expected.add(7);
        expected.add(6);
        expected.add(4);

        Assert.assertEquals(expected, postorder);
    }

    @Test
    public void testPostorderWithSingleNode() {
        TreeNode<Integer> root = new TreeNode<>(1);

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> postorder = traversals.postorder(root);

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);

        Assert.assertEquals(expected, postorder);
    }

    @Test
    public void testPostorderWithEmptyTree() {
        TreeNode<Integer> root = null;

        Traversals<Integer> traversals = new Traversals<>();

        List<Integer> postorder = traversals.postorder(root);

        List<Integer> expected = new java.util.ArrayList<>();

        Assert.assertEquals(expected, postorder);
    }
}
