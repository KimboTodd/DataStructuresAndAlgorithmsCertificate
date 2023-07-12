import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import org.junit.Assert;

public class ArrayListTest {

    ArrayList<Integer> arrayList;

    @Before
    public void setUp() {
        arrayList = new ArrayList<>();
    }

    @Test
    public void testAddToFront() {
        arrayList.addToFront(1);

        Assert.assertEquals(1, arrayList.size());
    }

    @Test
    public void testAdd10ToFront() {
        for (int i = 1; i <= 10; i++) {
            arrayList.addToFront(i);
        }

        Assert.assertEquals(10, arrayList.size());
    }

    @Test
    public void testAdd10ToBack() {
        for (int i = 1; i <= 10; i++) {
            arrayList.addToBack(i);
        }

        Assert.assertEquals(10, arrayList.size());
    }

    @Test
    public void testRemove10FromFront() {
        for (int i = 1; i <= 10; i++) {
            arrayList.addToBack(i);
        }

        for (int i = 1; i <= 10; i++) {
            int removed = arrayList.removeFromFront();
            Assert.assertEquals(i, removed);
        }

        Assert.assertEquals(0, arrayList.size());
    }

    @Test
    public void testRemove8FromFront() {
        for (int i = 1; i <= 9; i++) {
            arrayList.addToBack(i);
        }

        for (int i = 1; i <= 9; i++) {
            int removed = arrayList.removeFromFront();
            Assert.assertEquals(i, removed);
        }

        Assert.assertEquals(0, arrayList.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontWithEmptyList() {
        arrayList.removeFromFront();
    }

    @Test
    public void testRemove10FromBack() {
        for (int i = 1; i <= 10; i++) {
            arrayList.addToBack(i);
        }

        for (int i = 10; i >= 1; i--) {
            int removed = arrayList.removeFromBack();
            Assert.assertEquals(i, removed);
        }

        Assert.assertEquals(0, arrayList.size());
    }
}
