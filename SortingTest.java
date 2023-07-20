import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import org.junit.Assert;

public class SortingTest {
    private static final Comparator IntegerComparator = new IntegerComparator();

    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    @Test
    public void testBubbleSortWithEmptyList() {
        Integer[] emptyList = new Integer[0];
        Sorting.bubbleSort(emptyList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[0], emptyList);
    }

    @Test
    public void testBubbleSortWithOneElement() {
        Integer[] oneElementList = new Integer[] { 1 };
        Sorting.bubbleSort(oneElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1 }, oneElementList);
    }

    @Test
    public void testBubbleSortWithTwoElements() {
        Integer[] twoElementList = new Integer[] { 2, 1 };
        Sorting.bubbleSort(twoElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2 }, twoElementList);
    }

    @Test
    public void testBubbleSortWithThreeElements() {
        Integer[] threeElementList = new Integer[] { 3, 1, 2 };
        Sorting.bubbleSort(threeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, threeElementList);
    }

    @Test
    public void testBubbleSortWithRepeatedElements() {
        Integer[] repeatedElementList = new Integer[] { 2, 1, 3, 2, 1 };
        Sorting.bubbleSort(repeatedElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 1, 2, 2, 3 }, repeatedElementList);
    }

    @Test
    public void testBubbleSortWithNegativeElements() {
        Integer[] negativeElementList = new Integer[] { -2, -1, -3 };
        Sorting.bubbleSort(negativeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { -3, -2, -1 }, negativeElementList);
    }

    @Test
    public void testSelectionSortWithEmptyList() {
        Integer[] emptyList = new Integer[0];
        Sorting.selectionSort(emptyList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[0], emptyList);
    }

    @Test
    public void testSelectionSortWithOneElement() {
        Integer[] oneElementList = new Integer[] { 1 };
        Sorting.selectionSort(oneElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1 }, oneElementList);
    }

    @Test
    public void testSelectionSortWithTwoElements() {
        Integer[] twoElementList = new Integer[] { 2, 1 };
        Sorting.selectionSort(twoElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2 }, twoElementList);
    }

    @Test
    public void testSelectionSortWithThreeElements() {
        Integer[] threeElementList = new Integer[] { 3, 1, 2 };
        Sorting.selectionSort(threeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, threeElementList);
    }

    @Test
    public void testSelectionSortWithRepeatedElements() {
        Integer[] repeatedElementList = new Integer[] { 2, 1, 3, 2, 1 };
        Sorting.selectionSort(repeatedElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 1, 2, 2, 3 }, repeatedElementList);
    }

    @Test
    public void testSelectionSortWithNegativeElements() {
        Integer[] negativeElementList = new Integer[] { -2, -1, -3 };
        Sorting.selectionSort(negativeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { -3, -2, -1 }, negativeElementList);
    }

    @Test
    public void testInsertionSortWithEmptyList() {
        Integer[] emptyList = new Integer[0];
        Sorting.insertionSort(emptyList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[0], emptyList);
    }

    @Test
    public void testInsertionSortWithOneElement() {
        Integer[] oneElementList = new Integer[] { 1 };
        Sorting.insertionSort(oneElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1 }, oneElementList);
    }

    @Test
    public void testInsertionSortWithTwoElements() {
        Integer[] twoElementList = new Integer[] { 2, 1 };
        Sorting.insertionSort(twoElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2 }, twoElementList);
    }

    @Test
    public void testInsertionSortWithThreeElements() {
        Integer[] threeElementList = new Integer[] { 3, 1, 2 };
        Sorting.insertionSort(threeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, threeElementList);
    }

    @Test
    public void testInsertionSortWithRepeatedElements() {
        Integer[] repeatedElementList = new Integer[] { 2, 1, 3, 2, 1 };
        Sorting.insertionSort(repeatedElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 1, 2, 2, 3 }, repeatedElementList);
    }

    @Test
    public void testInsertionSortWithNegativeElements() {
        Integer[] negativeElementList = new Integer[] { -2, -1, -3 };
        Sorting.insertionSort(negativeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { -3, -2, -1 }, negativeElementList);
    }

    @Test
    public void testMergeSortWithEmptyList() {
        Integer[] emptyList = new Integer[0];
        Sorting.mergeSort(emptyList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[0], emptyList);
    }

    @Test
    public void testMergeSortWithOneElement() {
        Integer[] oneElementList = new Integer[] { 1 };
        Sorting.mergeSort(oneElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1 }, oneElementList);
    }

    @Test
    public void testMergeSortWithTwoElements() {
        Integer[] twoElementList = new Integer[] { 2, 1 };
        Sorting.mergeSort(twoElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2 }, twoElementList);
    }

    @Test
    public void testMergeSortWithThreeElements() {
        Integer[] threeElementList = new Integer[] { 3, 1, 2 };
        Sorting.mergeSort(threeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, threeElementList);
    }

    @Test
    public void testMergeSortWithRepeatedElements() {
        Integer[] repeatedElementList = new Integer[] { 2, 1, 3, 2, 1 };
        Sorting.mergeSort(repeatedElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { 1, 1, 2, 2, 3 }, repeatedElementList);
    }

    @Test
    public void testMergeSortWithNegativeElements() {
        Integer[] negativeElementList = new Integer[] { -2, -1, -3 };
        Sorting.mergeSort(negativeElementList, IntegerComparator);
        Assert.assertArrayEquals(new Integer[] { -3, -2, -1 }, negativeElementList);
    }

    @Test
    public void testLsdRadixSortWithEmptyList() {
        int[] emptyList = new int[0];
        Sorting.lsdRadixSort(emptyList);
        Assert.assertArrayEquals(new int[0], emptyList);
    }

    @Test
    public void testLsdRadixSortWithOneElement() {
        int[] oneElementList = new int[] { 1 };
        Sorting.lsdRadixSort(oneElementList);
        Assert.assertArrayEquals(new int[] { 1 }, oneElementList);
    }

    @Test
    public void testLsdRadixSortWithTwoElements() {
        int[] twoElementList = new int[] { 2, 1 };
        Sorting.lsdRadixSort(twoElementList);
        Assert.assertArrayEquals(new int[] { 1, 2 }, twoElementList);
    }

    @Test
    public void testLsdRadixSortWithThreeElements() {
        int[] threeElementList = new int[] { 3, 1, 2 };
        Sorting.lsdRadixSort(threeElementList);
        Assert.assertArrayEquals(new int[] { 1, 2, 3 }, threeElementList);
    }

    @Test
    public void testLsdRadixSortWithRepeatedElements() {
        int[] repeatedElementList = new int[] { 2, 1, 3, 2, 1 };
        Sorting.lsdRadixSort(repeatedElementList);
        Assert.assertArrayEquals(new int[] { 1, 1, 2, 2, 3 }, repeatedElementList);
    }

    @Test
    public void testLsdRadixSortWithNegativeElements() {
        int[] negativeElementList = new int[] { -2, -1, -3 };
        Sorting.lsdRadixSort(negativeElementList);
        Assert.assertArrayEquals(new int[] { -3, -2, -1 }, negativeElementList);
    }

    @Test
    public void testLsdRadixSortWithPositiveAndNegativeNumbers() {
        int[] arr = new int[] { 6789, -12345, -1234, 567, -12, 0, 123, -456, 7890, -678 };
        int[] expected = new int[] { -12345, -1234, -678, -456, -12, 0, 123, 567, 6789, 7890 };
        Sorting.lsdRadixSort(arr);
        Assert.assertArrayEquals(expected, arr);
    }
}