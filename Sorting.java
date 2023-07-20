import java.util.Comparator;
import java.util.LinkedList;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        var swapMade = true;
        var lastIndex = arr.length - 1;
        var lastSwappedIndex = arr.length - 1;
        for (int i = 0; i < arr.length - 1 && swapMade == true; i++) {
            swapMade = false;
            for (int j = 0; j < lastIndex; j++) {
                var result = comparator.compare(arr[j], arr[j + 1]);
                if (result > 0) {
                    swap(arr, j, j + 1);
                    swapMade = true;
                    lastSwappedIndex = j;
                }
            }
            lastIndex = lastSwappedIndex;
        }
    }

    private static final <T> void swap(T[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        for (int lastUnsortedIndex = arr.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            var indexOfMax = 0;
            for (int j = 1; j <= lastUnsortedIndex; j++) {
                var result = comparator.compare(arr[indexOfMax], arr[j]);
                if (result < 0) {
                    indexOfMax = j;
                }
            }

            swap(arr, lastUnsortedIndex, indexOfMax);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int indexUnsorted = 1; indexUnsorted < arr.length; indexUnsorted++) {
            for (int j = indexUnsorted; j > 0; j--) {
                // compare this element i with the previous item sub array
                var result = comparator.compare(arr[j], arr[j - 1]);
                if (result >= 0) {
                    break;
                }
                if (result < 0) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        var result = splitRecurse(arr, comparator, 0, arr.length);
        for (int i = 0; i < result.length; i++) {
            arr[i] = result[i];
        }
    }

    private static <T> T[] splitRecurse(T[] array, Comparator<T> comparator, int startIndex, int endIndexExclusive) {
        int arraySegmentLength = endIndexExclusive - startIndex;
        if (arraySegmentLength <= 1) {
            return array;
        }
        var splitIndex = startIndex + (arraySegmentLength / 2);

        splitRecurse(array, comparator, startIndex, splitIndex);

        splitRecurse(array, comparator, splitIndex, endIndexExclusive);

        merge(array, comparator, startIndex, splitIndex, splitIndex, endIndexExclusive);

        return array;
    }

    private static <T> void merge(T[] array, Comparator<T> comparator, int leftStart, int leftEnd, int rightStart,
            int rightEnd) {
        T[] returnArray = (T[]) new Object[rightEnd - leftStart];
        var leftMergeIndex = 0;
        var rightMergeIndex = 0;

        while (leftMergeIndex + leftStart < leftEnd) {
            if (rightMergeIndex + rightStart < rightEnd) {
                var comparison = comparator.compare(array[leftMergeIndex + leftStart],
                        array[rightMergeIndex + rightStart]);
                if (comparison <= 0) {
                    returnArray[leftMergeIndex + rightMergeIndex] = array[leftMergeIndex + leftStart];
                    leftMergeIndex++;
                } else {
                    returnArray[leftMergeIndex + rightMergeIndex] = array[rightMergeIndex + rightStart];
                    rightMergeIndex++;
                }
            } else {
                returnArray[leftMergeIndex + rightMergeIndex] = array[leftMergeIndex + leftStart];
                leftMergeIndex++;
            }
        }

        // if there are any remaining elements in right side of array, loop through them
        while (rightMergeIndex + rightStart < rightEnd) {
            returnArray[leftMergeIndex + rightMergeIndex] = array[rightMergeIndex + rightStart];
            rightMergeIndex++;
        }

        for (int i = 0; i < returnArray.length; i++) {
            array[i + leftStart] = returnArray[i];
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int max = Math.abs(arr[0]);
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
            }
        }

        int divisor = 1;
        var buckets = new LinkedList[19];

        while (max / divisor > 0) {

            for (int i = 0; i < arr.length; i++) {
                // var unshiftedBucket = arr[i] % (divisor * 10) / 10;
                var unshiftedBucket = arr[i] / divisor % 10;
                var bucket = unshiftedBucket + 9; // to account for -1 through -9
                if (buckets[bucket] == null) {
                    buckets[bucket] = new LinkedList<Integer>();
                }
                buckets[bucket].add(arr[i]);
            }

            var originalArrayIndex = 0;
            for (int j = 0; j < buckets.length; j++) {
                // loop through buckets
                if (buckets[j] != null) {
                    LinkedList<Integer> queue = buckets[j];
                    while (queue.peek() != null) {
                        arr[originalArrayIndex] = queue.pop();
                        originalArrayIndex++;
                    }
                }
            }

            divisor *= 10; // move the divisor to the next digit
        }
    }
}