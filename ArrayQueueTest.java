import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import org.junit.Assert;

public class ArrayQueueTest {

    ArrayQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new ArrayQueue<Integer>();
    }

    @Test
    public void testEnqueueOneElement() {
        queue.enqueue(1);

        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void testEnqueueMultipleElements() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        Assert.assertEquals(6, queue.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueWithNullData() {
        queue.enqueue(null);
    }

    @Test
    public void testDequeueMultipleElements() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        int dequeued1 = queue.dequeue();
        int dequeued2 = queue.dequeue();
        int dequeued3 = queue.dequeue();

        Assert.assertEquals(0, queue.size());
        Assert.assertEquals(1, dequeued1);
        Assert.assertEquals(2, dequeued2);
        Assert.assertEquals(3, dequeued3);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueWithEmptyQueue() {
        queue.dequeue();
    }

    @Test
    public void testEnqueueAndDequeueOneElement() {
        queue.enqueue(1);
        int dequeued = queue.dequeue();

        Assert.assertEquals(0, queue.size());
        Assert.assertEquals(1, dequeued);
    }

    @Test
    public void testEnqueueAndDequeueMultipleElements() {
        queue.enqueue(9);
        queue.enqueue(9);

        int dequeued1 = queue.dequeue();
        int dequeued2 = queue.dequeue();

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        int dequeued3 = queue.dequeue();
        int dequeued4 = queue.dequeue();
        int dequeued5 = queue.dequeue();

        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(9, dequeued1);
        Assert.assertEquals(9, dequeued2);
        Assert.assertEquals(0, dequeued3);
        Assert.assertEquals(1, dequeued4);
        Assert.assertEquals(2, dequeued5);
    }
}
