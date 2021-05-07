package it.edu.demo.recursiveaction;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class MergeSortAction extends RecursiveAction {

	private static final Logger LOGGER = Logger.getLogger(MergeSortAction.class.getName());
	private final int threshold;
	private int[] arrayToSort;

	public MergeSortAction(final int[] arrayToSort, final int threshold) {
		this.arrayToSort = arrayToSort;
		this.threshold = threshold;
	}

	@Override
	protected void compute() {
		if (arrayToSort.length <= threshold) {
			// sequential sort
			Arrays.sort(arrayToSort);
			return;
		}

		// Sort halves in parallel
		int midpoint = arrayToSort.length / 2;
		int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, midpoint);
		int[] rightArray = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);

		MergeSortAction left = new MergeSortAction(leftArray, threshold);
		MergeSortAction right = new MergeSortAction(rightArray, threshold);

		//invokeAll(left, right);
		left.fork();
		right.fork();

		left.join();
		right.join();
		// sequential merge
		arrayToSort = MergeSortMain.merge(left.getSortedArray(), right.getSortedArray());
	}

	public int[] getSortedArray() {
		return arrayToSort;
	}

}