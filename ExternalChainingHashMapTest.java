import org.junit.Test;

import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Assert;

public class ExternalChainingHashMapTest {

    @Test
    public void testPutWithDuplicateKey() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        var returnUnique = map.put("two", 2);
        var returnValueDuplicate = map.put("one", 3);

        Assert.assertEquals(3, map.get("one").getValue().intValue());
        Assert.assertEquals(2, map.get("two").getValue().intValue());
        Assert.assertEquals(1, returnValueDuplicate.intValue());
        Assert.assertEquals(null, returnUnique);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWithNullValue() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWithNullKey() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWithNullKeyAndValue() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put(null, null);
    }

    @Test
    public void testPutWithLargeNumberOfElements() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        for (int i = 0; i < 15; i++) {
            map.put(i, i * 2);
        }

        Assert.assertEquals(15, map.size());
        Assert.assertEquals(27, map.getTable().length);
    }

    @Test
    public void testPutWithCollidingKeys() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("FB", 1);
        map.put("Ea", 2);

        // Check if elements with colliding keys are correctly chained together
        var first = map.get("Ea");
        var second = first.getNext();
        Assert.assertEquals(2, first.getValue().intValue());
        Assert.assertEquals(1, second.getValue().intValue());
    }

    @Test
    public void testRemoveWithNullKey() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }

    @Test
    public void testRemoveWithNonexistentKey() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        assertThrows(NoSuchElementException.class, () -> map.remove("two"));
    }

    @Test
    public void testRemoveWithExistingKey() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        var removedValue = map.remove("two");
        Assert.assertEquals(2, removedValue.intValue());
        Assert.assertNull(map.get("two"));
    }

    @Test
    public void testRemoveLastElement() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        var removedValue = map.remove("one");
        Assert.assertEquals(1, removedValue.intValue());
        Assert.assertNull(map.get("one"));
    }

    @Test
    public void testRemoveFirstElement() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        var removedValue = map.remove("one");
        Assert.assertEquals(1, removedValue.intValue());
        Assert.assertNull(map.get("one"));
        Assert.assertEquals(2, map.get("two").getValue().intValue());
    }

    @Test
    public void testRemoveMiddleElement() {
        ExternalChainingHashMap<String, Integer> map = new ExternalChainingHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        var removedValue = map.remove("two");
        Assert.assertEquals(2, removedValue.intValue());
        Assert.assertNull(map.get("two"));
        Assert.assertEquals(1, map.get("one").getValue().intValue());
        Assert.assertEquals(3, map.get("three").getValue().intValue());
    }
}
