package it.edu.demo.recursiveaction;

import it.edu.demo.utils.Utils;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class MergeSortMain {

    private final int[] arrayToSort;
    private final int threshold;

    public MergeSortMain(final int[] arrayToSort, final int threshold) {
        this.arrayToSort = arrayToSort;
        this.threshold = threshold;
    }

    public int[] sequentialSort() {
        return sequentialSort(arrayToSort, threshold);
    }

    public static int[] sequentialSort(final int[] arrayToSort, int threshold) {
        if (arrayToSort.length < threshold) {
            Arrays.sort(arrayToSort);
            return arrayToSort;
        }

        int midpoint = arrayToSort.length / 2;

        int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, midpoint);
        int[] rightArray = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);

        leftArray = sequentialSort(leftArray, threshold);
        rightArray = sequentialSort(rightArray, threshold);

        return merge(leftArray, rightArray);
    }

    public static int[] merge(final int[] leftArray, final int[] rightArray) {
        int[] mergedArray = new int[leftArray.length + rightArray.length];
        int mergedArrayPos = 0;
        int leftArrayPos = 0;
        int rightArrayPos = 0;
        while (leftArrayPos < leftArray.length && rightArrayPos < rightArray.length) {
            if (leftArray[leftArrayPos] <= rightArray[rightArrayPos]) {
                mergedArray[mergedArrayPos] = leftArray[leftArrayPos];
                leftArrayPos++;
            } else {
                mergedArray[mergedArrayPos] = rightArray[rightArrayPos];
                rightArrayPos++;
            }
            mergedArrayPos++;
        }

        while (leftArrayPos < leftArray.length) {
            mergedArray[mergedArrayPos] = leftArray[leftArrayPos];
            leftArrayPos++;
            mergedArrayPos++;
        }

        while (rightArrayPos < rightArray.length) {
            mergedArray[mergedArrayPos] = rightArray[rightArrayPos];
            rightArrayPos++;
            mergedArrayPos++;
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        int[] arrayToSort = Utils.buildRandomIntArray(20000000);
        int[] expectedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);

        int nofProcessors = Runtime.getRuntime().availableProcessors();

        MergeSortMain shortestPathServiceSeq = new MergeSortMain(arrayToSort, nofProcessors);
        int[] actualArray = shortestPathServiceSeq.sequentialSort();

        Arrays.sort(expectedArray);

        assertThat(actualArray, is(expectedArray));

        int[] arrayToSortSingleThread = Utils.buildRandomIntArray(20000000);
        int[] arrayToSortMultiThread = Arrays.copyOf(arrayToSortSingleThread, arrayToSortSingleThread.length);

        nofProcessors = Runtime.getRuntime().availableProcessors();

        // SINGLE THREADED
        shortestPathServiceSeq = new MergeSortMain(arrayToSortSingleThread, nofProcessors);
        int[] sortSingleThreadArray = shortestPathServiceSeq.sequentialSort();

        // MULTI THREADED
        MergeSortAction mergeSortAction = new MergeSortAction(arrayToSortMultiThread, nofProcessors);

        ForkJoinPool forkJoinPool = new ForkJoinPool(nofProcessors);
        forkJoinPool.invoke(mergeSortAction);

        assertArrayEquals(sortSingleThreadArray, mergeSortAction.getSortedArray());
    }

}