import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;

public class BSTTest {

    private BST<Integer> bst;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test
    public void testadd() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testaddWithDuplicates() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(4);
        bst.add(2);
        bst.add(6);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNull() {
        bst.add(null);
    }

    @Test
    public void testRemove() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(4);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveWithSuccessor() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(2);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNull() {
        bst.remove(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveWithEmptyTree() {
        bst.remove(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveWithNonexistentValue() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(8);
    }

    @Test
    public void testRemoveRoot() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(4);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveLeaf() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(1);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveWithLeftSubtree() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(2);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveWithRightSubtree() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(6);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveWithRightSubtreeNoLeftChild() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        bst.remove(5);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);
        expected.add(7);

        Assert.assertEquals(expected, order);
    }

    @Test
    public void testRemoveWithRightSubtreeLeftChild() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(8);

        bst.remove(6);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(7);
        expected.add(8);

        Assert.assertEquals(expected, order);
    }

     @Test
    public void testRemoveWitOneElement() {
        bst.add(0);

        bst.remove(0);

        List<Integer> order = bst.order();

        List<Integer> expected = new java.util.ArrayList<>();

        Assert.assertEquals(expected, order);
    }
}
