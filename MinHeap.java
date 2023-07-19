import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (backingArray.length == size + 1) {

            var tempArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }

        size++;
        backingArray[size] = data;

        upHeap(size);
    }

    private void upHeap(int nodeIndex) {
        // index of parent
        var parentIndex = nodeIndex / 2;
        if (parentIndex == 0) {
            return;
        }
        // if this element is smaller than the parent
        var result = backingArray[parentIndex].compareTo(backingArray[nodeIndex]);
        if (result < 0) {
            return;
        }
        if (result > 0) {
            var rightSiblingIndex = parentIndex * 2 + 1;
            var leftSiblingIndex = parentIndex * 2;
            T smallerItem = backingArray[leftSiblingIndex];
            int smallerIndex = leftSiblingIndex;

            // if there is a right sibling
            if (rightSiblingIndex < backingArray.length & backingArray[rightSiblingIndex] != null) {
                var res = backingArray[leftSiblingIndex].compareTo(backingArray[rightSiblingIndex]);
                // if right sibling is smaller
                if (res > 0) {
                    smallerItem = backingArray[rightSiblingIndex];
                    smallerIndex = rightSiblingIndex;
                }
            }
            backingArray[smallerIndex] = backingArray[parentIndex];
            backingArray[parentIndex] = smallerItem;
        }

        upHeap(parentIndex);
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        var minElement = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        downHeap(1);

        return minElement;
    }

    private void downHeap(int nodeIndex) {
        var lastNodeHasChildren = size / 2;
        if (nodeIndex > lastNodeHasChildren) {
            return;
        }

        var leftSiblingIndex = nodeIndex * 2;
        var rightSiblingIndex = nodeIndex * 2 + 1;
        // this is probably redundant, must have children if we got here
        if (leftSiblingIndex <= size) {
            T leftChild = backingArray[leftSiblingIndex];
            T smallerItem = backingArray[leftSiblingIndex];
            int smallerIndex = leftSiblingIndex;

            if (rightSiblingIndex <= size) {
                var result = leftChild.compareTo(backingArray[rightSiblingIndex]);
                if (result < 0) {
                    // left child is already smaller
                }
                if (result > 0) {
                    smallerItem = backingArray[rightSiblingIndex];
                    smallerIndex = rightSiblingIndex;
                }
            }

            var resultParentChild = backingArray[nodeIndex].compareTo(smallerItem);
            if (resultParentChild < 0) {
                return;
            }
            if (resultParentChild > 0) {
                // child is larger, need to swap
                backingArray[smallerIndex] = backingArray[nodeIndex];
                backingArray[nodeIndex] = smallerItem;
                downHeap(smallerIndex);
            }
        }
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}