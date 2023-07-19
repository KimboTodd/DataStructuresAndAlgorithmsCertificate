import org.junit.Test;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

public class MinHeapTest {

    private MinHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    public void testAdd() {
        heap.add(4);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);

        Integer[] expected = { null, 1, 2, 5, 4, 3, 6, 7, null, null, null, null, null };
        var actual = (Comparable[]) heap.getBackingArray();
        Integer[] converted = new Integer[actual.length];
        for (int i = 0; i < actual.length; i++) {
            converted[i] = (Integer) actual[i];
        }
        Assert.assertArrayEquals(expected, converted);
    }

    @Test
    public void testAddToEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);

        Integer[] expected = { null, 1, null, null, null, null, null, null, null, null, null, null, null };
        var actual = (Comparable[]) heap.getBackingArray();
        Integer[] converted = new Integer[actual.length];
        for (int i = 0; i < actual.length; i++) {
            converted[i] = (Integer) actual[i];
        }
        Assert.assertArrayEquals(expected, converted);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNull() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(4);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);
        heap.add(null);
    }

    @Test
    public void testAddWithUnexpectedContent() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(7);
        heap.add(14);
        heap.add(21);
        heap.add(28);
        heap.add(35);
        heap.add(42);
        heap.add(49);
        heap.add(56);
        heap.add(63);

        heap.add(0);

        Integer[] expected = { null, 0, 7, 21, 28, 14, 42, 49, 56, 63, 35, null, null };
        var actual = (Comparable[]) heap.getBackingArray();
        Integer[] converted = new Integer[actual.length];
        for (int i = 0; i < actual.length; i++) {
            converted[i] = (Integer) actual[i];
        }
        Assert.assertArrayEquals(expected, converted);
    }

    @Test
    public void testAddContent() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(0);
        heap.add(14);
        heap.add(21);
        heap.add(28);
        heap.add(35);
        heap.add(42);

        heap.add(7);

        Integer[] expected = { null, 0, 14, 7, 28, 35, 42, 21, null, null, null, null, null };
        var actual = (Comparable[]) heap.getBackingArray();
        Integer[] converted = new Integer[actual.length];
        for (int i = 0; i < actual.length; i++) {
            converted[i] = (Integer) actual[i];
        }
        Assert.assertArrayEquals(expected, converted);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.remove();
    }

    @Test
    public void testRemoveFromHeapWithOneElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        var result = heap.remove();
        Assert.assertEquals(1, result.intValue());
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void testRemoveFromHeapWithDuplicates() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(4);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);
        heap.add(1);
        heap.add(2);
        heap.add(3);

        var result1 = heap.remove();
        Assert.assertEquals(1, result1.intValue());
        Assert.assertEquals(9, heap.size());

        var result2 = heap.remove();
        Assert.assertEquals(1, result2.intValue());
        Assert.assertEquals(8, heap.size());

        var result3 = heap.remove();
        Assert.assertEquals(2, result3.intValue());
        Assert.assertEquals(7, heap.size());

        var result4 = heap.remove();
        Assert.assertEquals(2, result4.intValue());
        Assert.assertEquals(6, heap.size());

        var result5 = heap.remove();
        Assert.assertEquals(3, result5.intValue());
        Assert.assertEquals(5, heap.size());

        var result6 = heap.remove();
        Assert.assertEquals(3, result6.intValue());
        Assert.assertEquals(4, heap.size());

        var result7 = heap.remove();
        Assert.assertEquals(4, result7.intValue());
        Assert.assertEquals(3, heap.size());

        var result8 = heap.remove();
        Assert.assertEquals(5, result8.intValue());
        Assert.assertEquals(2, heap.size());

        var result9 = heap.remove();
        Assert.assertEquals(6, result9.intValue());
        Assert.assertEquals(1, heap.size());

        var result10 = heap.remove();
        Assert.assertEquals(7, result10.intValue());
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void testAddToFullHeap() {
        heap.add(1);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(12);
        heap.add(13);
        heap.add(14);
    }

    @Test
    public void testAddToEmptyHeapTwo() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        var actual = heap.remove();
        Assert.assertEquals(1, actual.intValue());
    }

    @Test
    public void testRemoveFromHeapWithDifferentValues() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(4);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);

        var result1 = heap.remove();
        Assert.assertEquals(1, result1.intValue());
        Assert.assertEquals(6, heap.size());

        var result2 = heap.remove();
        Assert.assertEquals(2, result2.intValue());
        Assert.assertEquals(5, heap.size());

        var result3 = heap.remove();
        Assert.assertEquals(3, result3.intValue());
        Assert.assertEquals(4, heap.size());

        var result4 = heap.remove();
        Assert.assertEquals(4, result4.intValue());
        Assert.assertEquals(3, heap.size());

        var result5 = heap.remove();
        Assert.assertEquals(5, result5.intValue());
        Assert.assertEquals(2, heap.size());

        var result6 = heap.remove();
        Assert.assertEquals(6, result6.intValue());
        Assert.assertEquals(1, heap.size());

        var result7 = heap.remove();
        Assert.assertEquals(7, result7.intValue());
        Assert.assertEquals(0, heap.size());
    }
}
