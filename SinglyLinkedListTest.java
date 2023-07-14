import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListTest {

    SinglyLinkedList<Integer> linkedList;

    @Before                                         
    public void setUp() {
        // create a new empty linkedList
        linkedList = new SinglyLinkedList<>();
    }

    @Test                                               
    public void testAddToFront() {
        linkedList.addToFront(1);

        Assert.assertEquals(1, linkedList.size());
    }

    @Test(expected = IllegalArgumentException.class)                                               
    public void testAddToFrontWithNoData() {
        linkedList.addToFront(null);
    }

    @Test                                               
    public void testAddToBack() {
        linkedList.addToBack(1);

        Assert.assertEquals(1, linkedList.size());
    }

     @Test                                               
    public void testAddToBackWithOneElement() {
        linkedList.addToBack(2);

        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals((Integer) 2, linkedList.getHead().getData());
    }

    @Test                                               
    public void testAddToBackWithMultipleElements() {
        linkedList.addToBack(1);
        linkedList.addToBack(2);
        linkedList.addToBack(3);

        Assert.assertEquals(3, linkedList.size());
        Assert.assertEquals((Integer) 1, linkedList.getHead().getData());
        Assert.assertEquals((Integer) 3, linkedList.getTail().getData());

    }

       @Test(expected = IllegalArgumentException.class)                                               
    public void testAddToBackWithNullData() {
        linkedList.addToBack(null);
    }

       @Test(expected = NoSuchElementException.class)                                               
    public void testRemoveFromFrontWithEmptyList() {
        linkedList.removeFromFront();
    }

    @Test                                               
    public void testRemoveFromFrontWithOneElement() {
        linkedList.addToFront(5);
        int removed = linkedList.removeFromFront();

        Assert.assertEquals(0, linkedList.size());
        Assert.assertEquals(5, removed);
    }

    @Test                                               
    public void testRemoveFromFrontWithMultipleElements() {
        linkedList.addToFront(1);
        linkedList.addToFront(2);
        linkedList.addToFront(3);

        int removed1 = linkedList.removeFromFront();
        int removed2 = linkedList.removeFromFront();

        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals(3, removed1);
        Assert.assertEquals(2, removed2);
    }

   @Test(expected = NoSuchElementException.class)                                               
    public void testRemoveFromBackWithEmptyList() {
        linkedList.removeFromBack();
    }

    @Test                                               
    public void testRemoveFromBackWithOneElement() {
        linkedList.addToBack(1);
        int removed = linkedList.removeFromBack();

        Assert.assertEquals(0, linkedList.size());
        Assert.assertEquals(1, removed);
    }

    @Test                                               
    public void testRemoveFromBackWithMultipleElements() {
        linkedList.addToBack(1);
        linkedList.addToBack(2);
        linkedList.addToBack(3);

        int removed1 = linkedList.removeFromBack();
        int removed2 = linkedList.removeFromBack();
        int removed3 = linkedList.removeFromBack();

        Assert.assertEquals(0, linkedList.size());
        Assert.assertEquals(3, removed1);
        Assert.assertEquals(2, removed2);
        Assert.assertEquals(1, removed3);
    }

    // @Test(expected = NoSuchElementException.class)                                               
    // public void testRemoveFromFrontWithEmptyList() {
    //     linkedList.removeFromFront();
    // }

    // @Test(expected = NoSuchElementException.class)                                               
    // public void testRemoveFromBackWithEmptyList() {
    //     linkedList.removeFromBack();
    // }
}
